package pl.edu.agh.eaiib.io.kotlindemo

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import pl.edu.agh.eaiib.io.kotlindemo.model.SensorData
import pl.edu.agh.eaiib.io.kotlindemo.rest.SensorApiService
import pl.edu.agh.eaiib.io.kotlindemo.view.SensorChangedEventHandler

class MainActivity : Activity() {
    private lateinit var sensorEventHandler: SensorChangedEventHandler

    public override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onResume() {
        super.onResume()

        var lastUpdate = 0L
        val sensorApi = SensorApiService.create(getConfigProperty(DATA_SERVER_URL_KEY, applicationContext))
        sensorEventHandler = SensorChangedEventHandler(Sensor.TYPE_ACCELEROMETER) {
            val timestamp = it.timestamp
            if (timestamp - lastUpdate > INTERVAL) {
                Log.d("Timestamp: ", "$timestamp, values: ${it.values}")
                sensorApi.send(it)
                lastUpdate = timestamp
            }
        }

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(sensorEventHandler.sensorType)
        sensorManager.registerListener(sensorEventHandler, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(sensorEventHandler)
    }
}

private fun SensorApiService.send(sensorEvent: SensorEvent) {
    val sensorId = sensorEvent.sensor.type.toString()
    val values = arrayOf(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2])
    val sensorData = SensorData(sensorId, values, sensorEvent.timestamp)

    val publishProcessor = PublishProcessor.create<Unit>()
    this.addSensorData(sensorData)
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext(Flowable.empty())
            .subscribe {
                publishProcessor.onNext(Unit)
            }
}

const val INTERVAL = 1000L * 1000L * 1000L
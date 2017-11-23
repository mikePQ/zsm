package pl.edu.agh.eaiib.io.kotlindemo

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import pl.edu.agh.eaiib.io.kotlindemo.view.SensorChangedEventHandler

class MainActivity : Activity() {
    private lateinit var sensorEventHandler: SensorChangedEventHandler

    public override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        var lastUpdate = 0L
        sensorEventHandler = SensorChangedEventHandler(Sensor.TYPE_ACCELEROMETER) {
            val timestamp = it.timestamp
            if (timestamp - lastUpdate > INTERVAL) {
                Log.d("Timestamp: ", "$timestamp, values: ${it.values}")
            }

            lastUpdate = timestamp
        }
    }

    override fun onResume() {
        super.onResume()

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

const val INTERVAL = 1000L * 1000L * 1000L
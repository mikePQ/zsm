package pl.edu.agh.eaiib.io.kotlindemo.view

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class SensorChangedEventHandler(val sensorType: Int,
                                private val handler: (SensorEvent) -> Unit) : SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        if (sensorType == sensorEvent.sensor.type) {
            handler.invoke(sensorEvent)
        }
    }
}

package pl.edu.agh.eaiib.io.zsm.kotlindemo.model

import org.springframework.data.annotation.Id

class SensorData(private val sensorId: String,
                 private val values: Array<Float>,
                 private val timestamp: Long) {
    @Id
    val id: String = "$sensorId-$timestamp"
}
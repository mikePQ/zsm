package pl.edu.agh.eaiib.io.kotlindemo.model

data class SensorData(private val sensorId: String,
                      private val values: Array<Float>,
                      private val timestamp: Long)
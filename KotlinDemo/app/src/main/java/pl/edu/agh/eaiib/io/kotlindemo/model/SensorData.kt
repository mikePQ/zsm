package pl.edu.agh.eaiib.io.kotlindemo.model

data class SensorData(private val sensorId: String,
                      private val values: Array<Float>,
                      private val timestamp: Long)


data class Result(val totalCount: Int,
                  val incompleteResults: Boolean,
                  val items: List<SensorData>)
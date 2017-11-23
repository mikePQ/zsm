package pl.edu.agh.eaiib.io.kotlindemo.rest

import io.reactivex.Flowable
import pl.edu.agh.eaiib.io.kotlindemo.model.SensorData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface SensorApiService {

    @POST("sensorData")
    fun addSensorData(@Body sensorData: SensorData): Flowable<Any>

    companion object Factory {
        fun create(serverBaseUrl: String = ""): SensorApiService {
            if (serverBaseUrl.isEmpty()) {
                return EmptySensorApiService()
            }

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(serverBaseUrl)
                    .build()

            return retrofit.create(SensorApiService::class.java)
        }
    }
}

class EmptySensorApiService : SensorApiService {
    override fun addSensorData(sensorData: SensorData): Flowable<Any> = Flowable.empty()
}
package pl.edu.agh.eaiib.io.kotlindemo.rest

import io.reactivex.Observable
import pl.edu.agh.eaiib.io.kotlindemo.model.Result
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SensorApiService {

    @GET("search/findAll")
    fun getAll(): Observable<Result>


    companion object Factory {
        fun create(): SensorApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://localhost:9090/sensorData/")
                    .build()

            return retrofit.create(SensorApiService::class.java)
        }
    }
}
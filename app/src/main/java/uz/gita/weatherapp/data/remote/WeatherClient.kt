package uz.gita.weatherapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.weatherapp.utils.BASE_URL_WEATHER

object WeatherClient {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_WEATHER)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getWeatherApi():WeatherApi = retrofit.create(WeatherApi::class.java)
}
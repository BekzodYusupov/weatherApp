package uz.gita.weatherapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.weatherapp.utils.BASE_URL_GEO

object GeoClient {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GEO)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getGeoApi():GeoApi = retrofit.create(GeoApi::class.java)
}
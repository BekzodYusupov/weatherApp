package uz.gita.weatherapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.utils.API_KEY

interface ForecastApi {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("appid") appId:String = API_KEY
    ):Response<ForecastResponseData>
}
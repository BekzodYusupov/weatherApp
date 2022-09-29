package uz.gita.weatherapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.utils.API_KEY

//api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API key}
//https://api.openweathermap.org/data/2.5/weather?lat=41.3123363&lon=69.2787079&appid=09bf62a1cab5e87a6b08bad504ca95c7

interface WeatherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: String = "41.3123363",
        @Query("lon") lon: String = "69.2787079",
        @Query("appid") appid: String = API_KEY
    ): Response<WeatherResponseData>
}
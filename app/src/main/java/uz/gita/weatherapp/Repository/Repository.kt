package uz.gita.weatherapp.Repository

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

interface Repository {
    suspend fun getData(country: String)
    suspend fun getWeather(): Flow<WeatherResponseData>
    suspend fun getForecast():Flow<ForecastResponseData>
}
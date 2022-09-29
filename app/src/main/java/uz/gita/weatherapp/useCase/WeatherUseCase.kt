package uz.gita.weatherapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

interface WeatherUseCase {
    suspend fun getWeather(): Flow<WeatherResponseData>
}
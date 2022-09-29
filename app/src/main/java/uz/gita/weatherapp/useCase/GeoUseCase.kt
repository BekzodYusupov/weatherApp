package uz.gita.weatherapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

interface GeoUseCase {
    suspend fun getData(country: String)
}
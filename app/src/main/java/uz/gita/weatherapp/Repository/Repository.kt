package uz.gita.weatherapp.Repository

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.data.model.geo.GeoResponseData
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

interface Repository {
    suspend fun getData(country:String):Flow<WeatherResponseData>
}
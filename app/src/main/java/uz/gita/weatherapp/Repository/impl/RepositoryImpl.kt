package uz.gita.weatherapp.Repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.weatherapp.Repository.Repository
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.data.remote.GeoClient
import uz.gita.weatherapp.data.remote.WeatherClient

class RepositoryImpl : Repository {
    private val geoApi = GeoClient.getGeoApi()
    private val weatherApi = WeatherClient.getWeatherApi()

    override suspend fun getData(country: String): Flow<WeatherResponseData> {
        Log.d("TTT", "entered inside getData - in repository $country")
        val responseFlow = flow {
            Log.d("TTT", "entered inside flow - in repository")
            val geoResponse = geoApi.getData(country = country)
            if (geoResponse.isSuccessful) {
                if (geoResponse.body() != null) {
                    Log.d("TTT", "response success Geo - in repository")
                    val lat: String = geoResponse.body()!![0].lat.toString()
                    val lon: String = geoResponse.body()!![0].lon.toString()
                    val weatherResponse = weatherApi.getWeatherData(lat = lat, lon = lon)

                    if (weatherResponse.isSuccessful) {
                        if (weatherResponse.body() != null) {
                            emit(weatherResponse.body()!!)
                        }
                        Log.d("TTT", "response success Weather - in repository")

                        Log.d("TTT", "${weatherResponse.body()} and ${weatherResponse.message()}")
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                Log.d("TTT", "xatolik - ${it.message}")
            }
        return responseFlow
    }
}
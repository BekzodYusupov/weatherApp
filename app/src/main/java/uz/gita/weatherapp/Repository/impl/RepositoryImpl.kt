package uz.gita.weatherapp.Repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import uz.gita.weatherapp.Repository.Repository
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.data.remote.ForecastApi
import uz.gita.weatherapp.data.remote.GeoApi
import uz.gita.weatherapp.data.remote.WeatherApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val geoApi: GeoApi,
    private val weatherApi: WeatherApi,
    private val foreCastApi: ForecastApi
) : Repository {
    private lateinit var lat: String
    private lateinit var lon: String

    override suspend fun getData(country: String) {
        val response = geoApi.getData(country)
        Log.d("oooo","in repository getData() ishladi")
        if (response.isSuccessful) {
            Log.d("oooo","in repository getData(), response is successful ishladi")
            response.body()?.get(0)?.let {
                lat = it.lat.toString()
                lon = it.lon.toString()
            }
        }
    }

    override suspend fun getWeather(): Flow<WeatherResponseData> {
        Log.d("oooo","in repository getWeather() ishladi")

        val responseFlow = flow<WeatherResponseData> {
            val response = weatherApi.getWeatherData(lat, lon)
            if (response.isSuccessful) {
                Log.d("oooo","in repository getWeather(), response is successful ishladi")

                response.body()?.let {
                    emit(it)
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {  }
        return responseFlow
    }

    override suspend fun getForecast(): Flow<ForecastResponseData> {
        Log.d("oooo","in repository getForeCast() ishladi")
        val responseFlow = flow<ForecastResponseData> {
            val response = foreCastApi.getForecast(lat, lon)
            if (response.isSuccessful) {
                Log.d("oooo","in repository getForecast(), response is successful ishladi")
                response.body()?.let {
                    emit(it)
                }
            } else {
                Log.d("oooo","in repository getForecast(), response is unsuccessful ishladi")
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                Log.d("oooo","in repository getForecast() --- catch ---, ${it.message}\n ${it.cause}\n$it")

            }
        return responseFlow
    }
}
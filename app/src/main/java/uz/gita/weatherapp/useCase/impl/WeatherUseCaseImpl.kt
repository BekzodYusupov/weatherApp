package uz.gita.weatherapp.useCase.impl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.Repository.Repository
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.useCase.WeatherUseCase
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(
    private val repository: Repository
): WeatherUseCase {
    override suspend fun getWeather(): Flow<WeatherResponseData> {
        return repository.getWeather()
        Log.d("oooo","weather useCase ishladiii")
    }
}
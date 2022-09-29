package uz.gita.weatherapp.useCase.impl

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.Repository.Repository
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.useCase.GeoUseCase
import javax.inject.Inject

class GeoUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GeoUseCase {
    override suspend fun getData(country: String) {
        repository.getData(country)
        Log.d("oooo","Geo useCase ishladiii")
    }
}
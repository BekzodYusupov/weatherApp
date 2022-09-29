package uz.gita.weatherapp.useCase.impl

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.Repository.Repository
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.useCase.GraphUseCase
import javax.inject.Inject

class GraphUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GraphUseCase {
    override suspend fun getForecast(): Flow<ForecastResponseData> {
        return repository.getForecast()
        Log.d("oooo","Graph useCase ishladiii")
    }
}
package uz.gita.weatherapp.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData

interface GraphUseCase {
    suspend fun getForecast(): Flow<ForecastResponseData>
}
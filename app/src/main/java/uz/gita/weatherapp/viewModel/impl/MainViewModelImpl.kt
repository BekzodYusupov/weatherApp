package uz.gita.weatherapp.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.weatherapp.data.model.weather.WeatherResponseData
import uz.gita.weatherapp.useCase.GeoUseCase
import uz.gita.weatherapp.useCase.WeatherUseCase
import uz.gita.weatherapp.viewModel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val useCase: GeoUseCase,
    private val weatherUseCase: WeatherUseCase
) : MainViewModel, ViewModel() {
    override val data = MutableLiveData<WeatherResponseData>()
    override val chartScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun getData(country: String) {
        viewModelScope.launch {
            useCase.getData(country)
            weatherUseCase.getWeather().collectLatest {
                data.postValue(it)
            }
        }
    }

    override fun triggerChartScreen() {
        chartScreenLiveData.value = Unit
    }
}
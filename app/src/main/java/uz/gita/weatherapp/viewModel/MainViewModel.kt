package uz.gita.weatherapp.viewModel

import androidx.lifecycle.LiveData
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

interface MainViewModel {
    val data: LiveData<WeatherResponseData>
    val chartScreenLiveData:LiveData<Unit>

    fun getData(country: String)
    fun triggerChartScreen()
}
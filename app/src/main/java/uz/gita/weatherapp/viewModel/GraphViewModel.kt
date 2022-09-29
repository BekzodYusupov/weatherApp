package uz.gita.weatherapp.viewModel

import androidx.lifecycle.LiveData
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData

interface GraphViewModel {
    val chartLiveData:LiveData<ForecastResponseData>
    fun getData(country:String)

}
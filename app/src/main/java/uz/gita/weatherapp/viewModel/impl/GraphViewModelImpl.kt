package uz.gita.weatherapp.viewModel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.useCase.GeoUseCase
import uz.gita.weatherapp.useCase.GraphUseCase
import uz.gita.weatherapp.viewModel.GraphViewModel
import javax.inject.Inject

@HiltViewModel
class GraphViewModelImpl @Inject constructor(
    private val graphUseCase: GraphUseCase,
    private val geoUseCase: GeoUseCase
) : GraphViewModel, ViewModel() {
    override val chartLiveData: MutableLiveData<ForecastResponseData> = MutableLiveData()

    override fun getData(country: String) {
        Log.d("oooo","graph ViewModel ishladi.... $geoUseCase and $graphUseCase")
        viewModelScope.launch {
            geoUseCase.getData(country)
            graphUseCase.getForecast().collectLatest {
                chartLiveData.value = it
                val result = it
                Log.d("oooo", "result ------ $result}")
            }
        }
    }
}
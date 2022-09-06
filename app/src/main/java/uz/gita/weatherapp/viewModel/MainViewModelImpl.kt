package uz.gita.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.weatherapp.Repository.impl.RepositoryImpl
import uz.gita.weatherapp.data.model.weather.WeatherResponseData

class MainViewModelImpl : ViewModel() {
    private val repo = RepositoryImpl()
    val data = MutableLiveData<WeatherResponseData>()

    fun getData(country: String) {
        viewModelScope.launch {
            Log.d("TTT", "${repo.getData(country).collect()}---from viewModel")
            repo.getData(country).collectLatest {
                data.postValue(it)
            }
        }
    }
}
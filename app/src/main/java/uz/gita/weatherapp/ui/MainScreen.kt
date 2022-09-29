package uz.gita.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.weatherapp.R
import uz.gita.weatherapp.databinding.ScreenMainBinding
import uz.gita.weatherapp.viewModel.MainViewModel
import uz.gita.weatherapp.viewModel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen:Fragment(R.layout.screen_main) {
    private val viewBinding:ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val viewModel:MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.chartScreenLiveData.observe(this){
            findNavController().navigate(R.id.action_mainScreen_to_graphScreen)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnSearch.setOnClickListener {
            val country = viewBinding.countryInput.text.toString()
            viewModel.getData(country.toLowerCase())
        }
        viewModel.data.observe(viewLifecycleOwner){
            viewBinding.apply {
                name.text = it.name
                mainHumidity.text = "Humidity: ${it.main.humidity}"
                mainTemp.text = "Temp: ${it.main.temp} Kelvin"
                weatherDescription.text = "Sky: ${it.weather[0].description}"
                windSpeed.text = "Wind Speed: ${it.wind.speed}m/s"
            }
        }


        viewBinding.graph.setOnClickListener {
            viewModel.triggerChartScreen()
        }
    }
}
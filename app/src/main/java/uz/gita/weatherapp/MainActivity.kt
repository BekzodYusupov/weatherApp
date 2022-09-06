package uz.gita.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import uz.gita.weatherapp.databinding.ActivityMainBinding
import uz.gita.weatherapp.viewModel.MainViewModelImpl

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModelImpl by viewModels()
    private lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSearch.setOnClickListener {
            val country = viewBinding.countryInput.text.toString()
            viewModel.getData(country.toLowerCase())
        }
        viewModel.data.observe(this){
            viewBinding.apply {
                name.text = it.name
                mainHumidity.text = "Humidity: ${it.main.humidity}"
                mainTemp.text = "Temp: ${it.main.temp} Kelvin"
                weatherDescription.text = "Sky: ${it.weather[0].description}"
                windSpeed.text = "Wind Speed: ${it.wind.speed}m/s"
            }
        }
    }
}
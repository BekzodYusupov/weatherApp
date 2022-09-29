package uz.gita.weatherapp.data.model.forecast

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
package uz.gita.weatherapp.data.model.forecast

data class ForecastResponseData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Unnamed>,
    val message: Int
)
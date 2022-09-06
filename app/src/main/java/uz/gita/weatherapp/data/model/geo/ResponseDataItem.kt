package uz.gita.weatherapp.data.model.geo

data class ResponseDataItem(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String
)
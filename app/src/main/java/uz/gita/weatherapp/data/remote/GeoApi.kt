package uz.gita.weatherapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.weatherapp.data.model.geo.GeoResponseData
import uz.gita.weatherapp.utils.API_KEY

interface GeoApi {

    @GET("direct")
    suspend fun getData(
        @Query("q") country:String = "Tashkent",
        @Query("appid") apiKey:String = API_KEY
    ): Response<GeoResponseData>
}
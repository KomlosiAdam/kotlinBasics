package hu.griddy.kotlinbasics.network

import hu.griddy.kotlinbasics.model.ColorResponse
import retrofit2.http.GET

interface ColorService {
    @GET("api/unknown")
    suspend fun getColors(
    ): ColorResponse
}
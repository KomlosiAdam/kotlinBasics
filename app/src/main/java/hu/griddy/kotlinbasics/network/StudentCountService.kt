package hu.griddy.kotlinbasics.network

import hu.griddy.kotlinbasics.model.StudentCount
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentCountService {
    @GET("2024/bastid/api/api.php")
    suspend fun getStudentCount(
        @Query("endpoint") endpoint: String
    ): StudentCount
}
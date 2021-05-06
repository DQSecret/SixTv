package com.example.sixtv.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/bubble/api/tv")
    suspend fun getTvAsync(): TvListRepo

    @GET("/bubble/api/menu")
    suspend fun getMenuAsync(
        @Query("tvId") tvId: String,
    ): MenuRepo
}
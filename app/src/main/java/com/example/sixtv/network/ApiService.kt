package com.example.sixtv.network

import retrofit2.http.GET

interface ApiService {

    @GET("/bubble/api/tv")
    suspend fun getTvAsync(): TvListRepo
}
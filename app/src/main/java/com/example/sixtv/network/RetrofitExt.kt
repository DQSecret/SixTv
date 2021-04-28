package com.example.sixtv.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitExt {

    private const val API_DOMAIN = "https://bubble.api.wh2work.com"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_DOMAIN)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    private val logger by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val Api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
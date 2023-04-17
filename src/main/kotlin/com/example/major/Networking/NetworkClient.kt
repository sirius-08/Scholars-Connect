package com.example.major.Networking

import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {
    private val BASE_URL = "https://serpapi.com/"
    private val networkClient = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val networkAPI: APIService
        get() = networkClient.create(APIService::class.java)
}
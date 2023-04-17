package com.example.major.Networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search.json?engine=google_scholar_profiles&")
    fun searchProfile(@Query("mauthors") mauthors: String, @Query("api_key") api_key: String): Call<SearchProfileResponse>

    @GET("search.json?engine=google_scholar_author&")
    fun getAuthorProfile(@Query("author_id") author_id: String, @Query("api_key") api_key: String): Call<AuthorProfileResponse>
}

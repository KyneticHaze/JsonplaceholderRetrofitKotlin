package com.example.jsonplaceholderretrofitkotlin.service

import com.example.jsonplaceholderretrofitkotlin.model.ApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    fun getAlbumsData() : Call<List<ApiModel>>
}
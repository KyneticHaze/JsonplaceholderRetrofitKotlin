package com.example.jsonplaceholderretrofitkotlin.service

import com.example.jsonplaceholderretrofitkotlin.model.ApiModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("K21-JSONDataSet/master/crypto.json")
    fun getAlbumsData() : Observable<List<ApiModel>>
}
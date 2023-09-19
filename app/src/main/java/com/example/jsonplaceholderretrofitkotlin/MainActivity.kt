package com.example.jsonplaceholderretrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonplaceholderretrofitkotlin.model.ApiModel
import com.example.jsonplaceholderretrofitkotlin.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var apiModels : List<ApiModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val getDataCall = retrofit.create(ApiService::class.java)
        val service = getDataCall.getAlbumsData()

        service.enqueue(object : Callback<List<ApiModel>> {
            override fun onResponse(
                call: Call<List<ApiModel>>,
                response: Response<List<ApiModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        apiModels = ArrayList(it)

                        for (apiModel : ApiModel in apiModels!!) {
                            println(apiModel.id)
                            println(apiModel.title)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ApiModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}
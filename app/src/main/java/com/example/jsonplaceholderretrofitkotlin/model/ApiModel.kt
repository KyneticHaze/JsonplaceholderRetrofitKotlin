package com.example.jsonplaceholderretrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class ApiModel(
    //@SerializedName("id")
    var currency : String,
    //@SerializedName("title")
    var price: String
)

package com.example.jsonplaceholderretrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class ApiModel(
    //@SerializedName("id")
    val id: Int,
    //@SerializedName("title")
    val title: String
)

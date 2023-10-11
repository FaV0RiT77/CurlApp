package com.example.curlapp

import com.google.gson.annotations.SerializedName

data class NasaAPI(
    val date: String,
    @SerializedName("explanation")
    val text: String,
    val title: String,
    @SerializedName("url")
    val image: String) {
}

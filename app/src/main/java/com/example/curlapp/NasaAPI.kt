package com.example.curlapp

import com.google.gson.annotations.SerializedName

data class NasaAPI(
    @SerializedName("date", alternate = ["earth_date"])
    val date: String,
    @SerializedName("explanation")
    val text: String?,
    val title: String?,
    @SerializedName("url", alternate = ["img_src"])
    val image: String?
)

class ApodArray: ArrayList<NasaAPI>()
class MartianArray: ArrayList<NasaAPI>()


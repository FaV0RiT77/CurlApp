package com.example.curlapp

import retrofit2.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface apiService {
    @GET("/apod?api_key=4nf26H20czhc8GqRbiB8fadE8PI3UH8darWvNel9")
    suspend fun getData(): Response<NasaAPI>
}

object RetrofitClient {

    fun getInstance(): Retrofit {
        val mHttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.curlapp

import retrofit2.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Executors

interface apiService {
    @GET("apod")
    fun getData(@Query("api_key") key: String = "4nf26H20czhc8GqRbiB8fadE8PI3UH8darWvNel9"): Call<NasaAPI>
    @GET("planetary/apod")
    fun getArray(@Query("count") count: Int = 20,
                 @Query("api_key") key: String = "4nf26H20czhc8GqRbiB8fadE8PI3UH8darWvNel9")
    : Call<ApodArray>
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getArrayMartian(@Query("sol") sol: Int = 1000,
                        @Query("page") page: Int = 1,
                        @Query("camera") camera: String = "mast",
                        @Query("api_key") key: String = "4nf26H20czhc8GqRbiB8fadE8PI3UH8darWvNel9"):
            Call<MartianResponse>
}
class MartianResponse {
    val photos: MartianArray = MartianArray()
}


object RetrofitClient {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
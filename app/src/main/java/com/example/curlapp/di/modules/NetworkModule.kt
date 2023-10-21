package com.example.curlapp.di.modules

import com.example.curlapp.RetrofitClient
import com.example.curlapp.apiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesInstance(retrofit: Retrofit): apiService {
        return RetrofitClient.getInstance().create(apiService::class.java)
    }
}
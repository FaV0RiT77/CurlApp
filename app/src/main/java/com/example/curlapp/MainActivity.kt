package com.example.curlapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.curlapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.nasa.gov/planetary/apod?api_key=4nf26H20czhc8GqRbiB8fadE8PI3UH8darWvNel9")
            .build()

        binding.getBtn.setOnClickListener {
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val getToData = Gson().fromJson(responseBody, NasaAPI::class.java)
                        val inputStream = URL(getToData.image).openStream()
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        runOnUiThread {
                            binding.imageIv.setImageBitmap(bitmap)
                            binding.responseTv.text = getToData.text
                        }

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "GET resulted in an error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(
                        applicationContext,
                        "GET resulted in onFailure",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        binding.get2Btn.setOnClickListener {
            val retrofit = RetrofitClient.getInstance()
            val apiInterface = retrofit.create(apiService::class.java)
            lifecycleScope.launchWhenCreated {
                try {
                    val response = apiInterface.getData()
                    if(response.isSuccessful) {
                        val inputStream = URL(response.body()?.image)
                            .openStream()
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        runOnUiThread {
//                            binding.imageIv.setImageBitmap(bitmap)
                            binding.responseTv.text = response.raw().toString()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            response.errorBody().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (Ex:Exception) {
                    Ex.localizedMessage?.let { it1 -> Log.e("Error", it1) }
                }
            }

        }

    }
}


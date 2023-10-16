package com.example.curlapp

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.curlapp.databinding.FragmentApodApiStarterBinding
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ApodApiStarterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApodApiStarterFragment : Fragment() {
    private lateinit var binding: FragmentApodApiStarterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_apod_api_starter, container, false )

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
//                        val imgUri = Uri.parse(getToData.image)
                        activity?.runOnUiThread {
//                            binding.imageIv.setImageURI(imgUri)
                            binding.responseTv.text = getToData.text
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "GET resulted in an error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(
                        context,
                        "GET resulted in onFailure",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        binding.get2Btn.setOnClickListener {
            val retrofit = RetrofitClient.getInstance()
            val apiInterface = retrofit.create(apiService::class.java)
            val call: retrofit2.Call<NasaAPI> = apiInterface.getData()
            call.enqueue(object: retrofit2.Callback<NasaAPI> {
                override fun onResponse(
                    call: retrofit2.Call<NasaAPI>,
                    response: retrofit2.Response<NasaAPI>
                ) {
                    val modal: NasaAPI? = response.body()
//                    val imgUri = Uri.parse(modal?.image)
                    activity?.runOnUiThread {
//                        binding.imageIv.setImageURI(imgUri)
                        binding.responseTv.text = modal?.text
                    }
                }

                override fun onFailure(call: retrofit2.Call<NasaAPI>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "GET resulted in onFailure",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

        }

        return binding.root
    }


}
package com.example.curlapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.curlapp.databinding.FragmentApodRecyclerBinding
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentApodRecyclerBinding
    @Volatile
    var list = ArrayList<NasaAPI>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_apod_recycler, container, false )

        val retrofit = RetrofitClient.getInstance().create(apiService::class.java)
        val call = retrofit.getArray().enqueue(object: Callback<ApodArray> {
            override fun onResponse(call: Call<ApodArray>, response: Response<ApodArray>) {
                val response = response.body()?.listIterator()
                if(response != null) {
                    while(response.hasNext()) {
                        val item: NasaAPI = response.next()
                        list.add(element = NasaAPI(
                            item.date,
                            item.text,
                            null,
                            item.image))
                        Log.i("items", item.date)
                    }

                }
                val call2 = retrofit.getArrayMartian().enqueue(object: Callback<MartianArray> {
                    override fun onResponse(call: Call<MartianArray>, response: Response<MartianArray>) {
                        val response = response.body()?.listIterator()
                        if(response != null) {
                            while(response.hasNext()) {
                                val item: NasaAPI = response.next()
                                list.add(element = NasaAPI(
                                    item.date,
                                    null,
                                    null,
                                    item.image))
                                Log.i("items2", item.date)
                            }

                        }
                    }

                    override fun onFailure(call: Call<MartianArray>, t: Throwable) {
                        Log.e("items", "Mars fucked up")
                    }

                })
            }

            override fun onFailure(call: Call<ApodArray>, t: Throwable) {
                Log.e("items", "Apod fucked up")
            }

        })

            binding.apodRv.apply{
                adapter = NasaAdapter(list)
                layoutManager = LinearLayoutManager(context)
            }






        return binding.root
    }


}
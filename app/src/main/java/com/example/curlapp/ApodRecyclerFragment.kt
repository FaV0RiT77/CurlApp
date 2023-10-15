package com.example.curlapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.curlapp.databinding.FragmentApodRecyclerBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApodRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentApodRecyclerBinding

    @Volatile
    var list = ArrayList<NasaAPI>()
    val list2 = ArrayList<NasaAPI>()
    private var adapter: NasaAdapter = NasaAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_apod_recycler, container, false)



        val retrofit = RetrofitClient.getInstance().create(apiService::class.java)
        retrofit.getArray().enqueue(object : Callback<ApodArray> {
            override fun onResponse(call: Call<ApodArray>, response: Response<ApodArray>) {
                val responseItem = response.body()?.listIterator()
                if (responseItem != null) {
                    while (responseItem.hasNext()) {
                        val item: NasaAPI = responseItem.next()
                        list.add(
                            element = NasaAPI(
                                item.date,
                                item.text,
                                null,
                                item.image
                            )
                        )
                        Log.i("items", item.date)
                        activity?.runOnUiThread {
//                            adapter.notifyDataSetChanged()
                            adapter.submitList(list)
                                                    }

                    }
                }

            }

            override fun onFailure(call: Call<ApodArray>, t: Throwable) {
                Log.e("items", "Apod fucked up")
            }

        })

        retrofit.getArrayMartian().enqueue(object : Callback<MartianResponse> {
            override fun onResponse(
                call: Call<MartianResponse>,
                response: Response<MartianResponse>
            ) {
                val responseItem = response.body()?.photos?.listIterator()
                if (responseItem != null) {
                    while (responseItem.hasNext()) {
                        val item: NasaAPI = responseItem.next()
                        list2.add(
                            element = NasaAPI(
                                item.date,
                                null,
                                null,
                                item.image
                            )
                        )
                        Log.i("items2", item.date)
                        activity?.runOnUiThread {
//                            adapter.notifyDataSetChanged()
                            adapter.submitList(list2)
                        }

                    }

                }
            }

            override fun onFailure(call: Call<MartianResponse>, t: Throwable) {
                val message = if (t.message != null) t.message else "An error occurred!"
                if (message != null) Log.e("items2", message)
                Log.e("items2", "Mars fucked up")
            }

        })

        binding.apodRv.apply {
            adapter = this@ApodRecyclerFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }



        return binding.root
    }


}
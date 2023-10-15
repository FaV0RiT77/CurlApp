package com.example.curlapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.curlapp.databinding.FragmentApodRecyclerBinding
import com.example.curlapp.databinding.FragmentMartianStarterBinding

class ApodRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentApodRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_apod_recycler, container, false )

        return binding.root
    }


}
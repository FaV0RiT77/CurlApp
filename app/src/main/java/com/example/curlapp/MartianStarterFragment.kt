package com.example.curlapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.curlapp.databinding.FragmentApodApiStarterBinding
import com.example.curlapp.databinding.FragmentMartianStarterBinding


class MartianStarterFragment : Fragment() {
    private lateinit var binding: FragmentMartianStarterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_martian_starter, container, false )

        return binding.root
    }
}
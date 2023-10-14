package com.example.curlapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.curlapp.databinding.FragmentMartianRecyclerBinding


class MartianRecyclerFragment : Fragment() {
    private lateinit var binding: FragmentMartianRecyclerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_martian_recycler, container, false )

        return binding.root
    }
}
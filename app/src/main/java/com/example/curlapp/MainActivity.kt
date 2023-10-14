package com.example.curlapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.curlapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        loadFragment(ApodApiStarterFragment())

        binding.bottomNv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.apod_itm -> {
                    loadFragment(ApodApiStarterFragment())
                    true
                }

                R.id.apodR_itm -> {
                    loadFragment(ApodRecyclerFragment())
                    true
                }

                R.id.martianR_itm -> {
                    loadFragment(MartianRecyclerFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_fl,fragment)
        transaction.commit()
    }

}



package com.example.banknoteproject.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banknoteproject.databinding.ActivityHomeScreenBinding


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

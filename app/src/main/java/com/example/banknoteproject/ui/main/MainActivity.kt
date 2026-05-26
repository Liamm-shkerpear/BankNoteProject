package com.example.banknoteproject.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banknoteproject.R
import com.example.banknoteproject.databinding.ActivityMainBinding
import com.example.banknoteproject.ui.main.adapter.MainAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) {v, insets ->
            val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBar.top, 0, 0)
            binding.bottomNav.setPadding(0, 10, 0, systemBar.bottom)
            insets
        }

        initView()
    }

    private fun initView() {
        binding.apply {
            viewPager2.adapter = MainAdapter(this@MainActivity)
            viewPager2.isUserInputEnabled = false
            bottomNav.setOnItemSelectedListener { item ->
                val position = when (item.itemId) {
                    R.id.nav_home -> 0
                    R.id.nav_collection -> 1
                    else -> return@setOnItemSelectedListener false
                }
                viewPager2.setCurrentItem(position, true)
                true
            }
        }

    }

}

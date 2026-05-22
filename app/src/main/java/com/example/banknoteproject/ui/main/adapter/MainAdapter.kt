package com.example.banknoteproject.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banknoteproject.ui.collection.CollectionFragment
import com.example.banknoteproject.ui.home.HomeFragment

class MainAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CollectionFragment()
            else -> HomeFragment()
        }
    }
}

package com.example.banknoteproject.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.banknoteproject.ui.onboarding.steps.StepOneFragment
import com.example.banknoteproject.ui.onboarding.steps.StepThreeFragment
import com.example.banknoteproject.ui.onboarding.steps.StepTwoFragment

class OnboardingAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StepOneFragment()
            1 -> StepTwoFragment()
            2 -> StepThreeFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}

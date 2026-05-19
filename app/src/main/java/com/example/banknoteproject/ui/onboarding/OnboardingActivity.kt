package com.example.banknoteproject.ui.onboarding

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.banknoteproject.databinding.ActivityOnboardingBinding
import com.example.banknoteproject.ui.home.HomeScreenActivity
import com.example.banknoteproject.ui.onboarding.adapter.OnboardingAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.jvm.java
import androidx.core.content.edit
import com.example.banknoteproject.data.domain.model.OnboardingClickState

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModel()
    private lateinit var onboardingAdapter: OnboardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })

        initView()
        initClickListener()
        bindViewModel()
    }

    private fun initView() {
        onboardingAdapter = OnboardingAdapter(this)
        binding.vpOnboarding.adapter = onboardingAdapter
        binding.vpOnboarding.isUserInputEnabled = false
        binding.vpOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateProgress(position)
                validateNextButton(viewModel.uiState.value, position)
            }
        })
    }

    @SuppressLint("CommitPrefEdits")
    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            val currentItem = binding.vpOnboarding.currentItem
            if (currentItem < 2) {
                binding.vpOnboarding.currentItem = currentItem + 1
            } else {
                val sharePref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
                sharePref.edit { putBoolean("isCompleted", true) }
                navigateToHomeScreen()
            }
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    validateNextButton(state, binding.vpOnboarding.currentItem)
                }
            }
        }
    }

    private fun updateProgress(position: Int) {
        val targetProgress = ((position + 1) * 100) / 3
        ObjectAnimator.ofInt(
            binding.pbOnboarding, "progress",
            binding.pbOnboarding.progress, targetProgress
        ).apply {
            duration = 500
            start()
        }
    }

    private fun validateNextButton(state: OnboardingClickState, currentPosition: Int) {
        val isAnswered = when (currentPosition) {
            0 -> state.isStepOneClick
            1 -> state.isStepTwoClick
            2 -> state.isStepThreeClick
            else -> false
        }
        binding.btnNext.isEnabled = isAnswered
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}

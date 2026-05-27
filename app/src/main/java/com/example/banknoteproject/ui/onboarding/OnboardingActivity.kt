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
import com.example.banknoteproject.ui.main.MainActivity
import com.example.banknoteproject.ui.onboarding.adapter.OnboardingAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.jvm.java
import androidx.core.content.edit
import com.example.banknoteproject.data.domain.entities.OnboardingClickState
import com.example.banknoteproject.utils.AppConstants

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
                validateNextButton(viewModel.uiState.value)
            }
        })
    }

    @SuppressLint("CommitPrefEdits")
    private fun initClickListener() {
        binding.btnNext.setOnClickListener {
            val currentItem = binding.vpOnboarding.currentItem
            if (currentItem < 2) {
                binding.vpOnboarding.currentItem = currentItem + 1
                viewModel.resetClickState()
            } else {
                val sharePref = getSharedPreferences(AppConstants.APP_PREFERENCES, MODE_PRIVATE)
                sharePref.edit { putBoolean(AppConstants.IS_COMPLETED, true) }
                navigateToHomeScreen()
            }
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    validateNextButton(state)
                }
            }
        }
    }

    private fun updateProgress(position: Int) {
        val targetProgress = ((position + 1) * 100) / 3
        ObjectAnimator.ofInt(
            binding.pbOnboarding, AppConstants.PROGRESS,
            binding.pbOnboarding.progress, targetProgress
        ).apply {
            duration = 500
            start()
        }
    }

    private fun validateNextButton(state: OnboardingClickState) {
        binding.btnNext.isEnabled = state.isClick
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

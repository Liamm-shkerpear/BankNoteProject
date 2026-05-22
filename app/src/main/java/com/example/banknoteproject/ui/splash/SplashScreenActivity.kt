package com.example.banknoteproject.ui.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.banknoteproject.databinding.ActivitySplashScreenBinding
import com.example.banknoteproject.ui.main.MainActivity
import com.example.banknoteproject.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.jvm.java

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val animator = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100)
        animator.duration = 3000
        animator.interpolator = DecelerateInterpolator()
        animator.start()

        lifecycleScope.launch {
            delay(3000)
            val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
            val isCompleted = sharedPref.getBoolean("isCompleted", false)
            if (isCompleted) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, OnboardingActivity::class.java))
            }
            finish()
        }
    }
}

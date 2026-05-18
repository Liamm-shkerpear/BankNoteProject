package com.example.banknoteproject.di.module

import com.example.banknoteproject.ui.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
}


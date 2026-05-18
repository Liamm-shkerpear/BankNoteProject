package com.example.banknoteproject.data.domain.repository

import com.example.banknoteproject.data.domain.entities.OnboardingQuiz
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getOnboardingData() : Flow<OnboardingQuiz?>
    suspend fun insertOnboardingData(onboarding: OnboardingQuiz)
    fun getOnboardingStatus() : Flow<Boolean>
}

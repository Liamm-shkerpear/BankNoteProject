package com.example.banknoteproject.data.repository

import com.example.banknoteproject.data.domain.entities.OnboardingQuiz
import com.example.banknoteproject.data.domain.repository.OnboardingRepository
import com.example.banknoteproject.data.source.local.dao.OnboardingDao
import kotlinx.coroutines.flow.Flow

class OnboardingRepositoryImpl(private val dao: OnboardingDao) : OnboardingRepository {
    override fun getOnboardingData(): Flow<OnboardingQuiz?> {
        return dao.getOnboardingData()
    }

    override suspend fun insertOnboardingData(onboarding: OnboardingQuiz) {
        return dao.insertOnboardingData(onboarding)
    }
    override fun getOnboardingStatus(): Flow<Boolean> {
        return dao.getOnboardingStatus()
    }
}

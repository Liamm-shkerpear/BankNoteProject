package com.example.banknoteproject.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.banknoteproject.data.domain.entities.OnboardingQuiz
import kotlinx.coroutines.flow.Flow

@Dao
interface OnboardingDao {
    @Query("SELECT * FROM onboarding_quiz WHERE id = 1")
    fun getOnboardingData(): Flow<OnboardingQuiz?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnboardingData(onboarding: OnboardingQuiz)

    @Query("SELECT is_completed FROM onboarding_quiz WHERE id = 1")
    fun getOnboardingStatus(): Flow<Boolean>
}

package com.example.banknoteproject.data.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "onboarding_quiz")
data class OnboardingQuiz(
    @PrimaryKey
    val id : Int = 1,
    @ColumnInfo(name = "step_one")
    val stepOne : String = "",
    @ColumnInfo(name = "step_two")
    val stepTwo : String = "",
    @ColumnInfo(name = "step_three")
    val stepThree : String = "",
    @ColumnInfo(name = "is_completed")
    val isCompleted : Boolean = false
) : Parcelable

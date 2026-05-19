package com.example.banknoteproject.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.banknoteproject.data.domain.model.OnboardingClickState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingClickState())
    val uiState = _uiState.asStateFlow()

    fun markStepOneAnswered() {
        _uiState.value = _uiState.value.copy(isStepOneClick = true)
    }

    fun markStepTwoAnswered() {
        _uiState.value = _uiState.value.copy(isStepTwoClick = true)
    }

    fun markStepThreeAnswered() {
        _uiState.value = _uiState.value.copy(isStepThreeClick = true)
    }
}

package com.example.banknoteproject.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.banknoteproject.data.domain.entities.OnboardingClickState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingClickState())
    val uiState = _uiState.asStateFlow()

    fun markStepAnswered() {
        _uiState.value = _uiState.value.copy(isClick = true)
    }
    fun resetClickState() {
        _uiState.value = _uiState.value.copy(isClick = false)
    }
}

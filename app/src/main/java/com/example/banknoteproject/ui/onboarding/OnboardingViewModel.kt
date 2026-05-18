package com.example.banknoteproject.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.OnboardingQuiz
import com.example.banknoteproject.data.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val repository: OnboardingRepository) : ViewModel() {
    private val _onboardingData = MutableStateFlow(OnboardingQuiz())
    val onboardingData = _onboardingData.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getOnboardingData().collect { data ->
                _onboardingData.value = data ?: OnboardingQuiz()
            }
        }
    }

    fun updateStepOne(answer: String) {
        _onboardingData.value = _onboardingData.value.copy(stepOne = answer)
    }
    fun updateStepTwo(answer: String) {
        _onboardingData.value = _onboardingData.value.copy(stepTwo = answer)
    }
    fun updateStepThree(answer: String) {
        _onboardingData.value = _onboardingData.value.copy(stepThree = answer)
    }

    fun insertOnboardingData() {
        _onboardingData.value = _onboardingData.value.copy(isCompleted = true)
        viewModelScope.launch {
            repository.insertOnboardingData(_onboardingData.value)
        }
    }

}

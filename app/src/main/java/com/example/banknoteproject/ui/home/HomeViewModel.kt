package com.example.banknoteproject.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository): ViewModel() {
    private val _recentData = MutableStateFlow<List<BanknoteItem>>(emptyList())
    val recentData = _recentData.asStateFlow()
    private val _randomData = MutableStateFlow<List<BanknoteItem>>(emptyList())
    val randomData = _randomData.asStateFlow()

    fun getRecentData() {
        viewModelScope.launch {
            try {
                val response = repository.getRecentData()
                if (response.isSuccessful) {
                    val allData = response.body()?.data ?: emptyList()
                    _recentData.value = allData.take(10)
                    _randomData.value = allData.shuffled().take(10)
                } else {
                    Log.e("HomeViewModel", "API Server Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error when call API: ", e)
            }
        }
    }
}

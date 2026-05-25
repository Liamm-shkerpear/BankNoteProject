package com.example.banknoteproject.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<BanknoteItem>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val _isEmpty = MutableStateFlow(false)
    val isEmpty = _isEmpty.asStateFlow()

    private var lastSearchQuery: String = ""

    init {
        getAllData()
    }

    private fun getAllData() {
        viewModelScope.launch {
            try {
                val response = repository.getAllItems()
                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    _searchResults.value = data
                    _isEmpty.value = data.isEmpty()
                } else {
                    _searchResults.value = emptyList()
                    _isEmpty.value = true
                }
            } catch (e: Exception) {
                _searchResults.value = emptyList()
                _isEmpty.value = true
            }
        }
    }

    fun searchItems(query: String) {
        val validQuery = query.trim()
        if (validQuery.isBlank()) {
            getAllData()
            lastSearchQuery = ""
            return
        }
        if (validQuery.equals(lastSearchQuery, ignoreCase = true)) {
            return
        }
        lastSearchQuery = validQuery

        viewModelScope.launch {
            try {
                val response = repository.searchItems(title = validQuery)
                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    _searchResults.value = data
                    _isEmpty.value = data.isEmpty()
                } else {
                    _searchResults.value = emptyList()
                    _isEmpty.value = true
                }
            } catch (e: Exception) {
                _searchResults.value = emptyList()
                _isEmpty.value = true
                }
            }
        }
    }


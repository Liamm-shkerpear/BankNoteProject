package com.example.banknoteproject.ui.search

import android.util.Log
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
    private var currentPage = 0
    private var isLastPage = false
    private var isLoading = false

    init {
        getAllData(isRefresh = true)
    }

    fun getAllData(isRefresh: Boolean = false) {
        if (isLoading || (!isRefresh && isLastPage)) return
        if (isRefresh) {
            currentPage = 0
            isLastPage = false
        }
        isLoading = true

        viewModelScope.launch {
            try {
                val response = repository.getAllItems(page = currentPage)
                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    if (isRefresh) {
                        _searchResults.value = data
                    } else {
                        _searchResults.value = _searchResults.value + data
                    }
                    if (data.size < 30 ) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                    _isEmpty.value = _searchResults.value.isEmpty()
                }
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }

    fun searchItems(query: String) {
        val validQuery = query.trim()
        if (validQuery.isBlank()) {
            getAllData(isRefresh = true)
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


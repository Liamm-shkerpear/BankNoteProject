package com.example.banknoteproject.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.repository.DetailRepository
import com.example.banknoteproject.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val collection: CollectionDao,
    private val repository: DetailRepository
) : ViewModel() {
    private val _isCollection = MutableStateFlow(false)
    val isCollection = _isCollection.asStateFlow()
    private val _detailData = MutableStateFlow<BanknoteItem?>(null)
    val detailData = _detailData.asStateFlow()

    fun getItemDetails(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getItemDetails(id)
                if (response.isSuccessful) {
                    _detailData.value = response.body()
                } else {
                    Log.e("DetailVM", "Lỗi API: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("DetailVM", "Lỗi mạng hoặc parse JSON: ", e)
            }
        }
    }
    fun checkCollection(itemId: String) {
        viewModelScope.launch {
            collection.isCollection(itemId).collect { isSaved ->
                _isCollection.value = isSaved
            }
        }
    }

    fun handleCollectionClick(item: BanknoteItem) {
        viewModelScope.launch {
            if (_isCollection.value) {
                collection.deleteCollection(item.id)
            } else {
                collection.insertCollection(item)
            }
        }
    }
}

package com.example.banknoteproject.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val collection: CollectionDao): ViewModel() {
    private val _isCollection = MutableStateFlow(false)
    val isCollection = _isCollection.asStateFlow()

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

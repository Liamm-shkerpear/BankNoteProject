package com.example.banknoteproject.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CollectionViewModel(private val collection: CollectionDao) : ViewModel() {
    private val _allCollection = MutableStateFlow<List<BanknoteItem>>(emptyList())
    private val _collectionItems = MutableStateFlow<List<BanknoteItem>>(emptyList())
    val collectionItems = _collectionItems.asStateFlow()
    private var lastQuery = ""

    init {
        viewModelScope.launch {
            collection.getAllCollection().collect { list ->
                _allCollection.value = list
                collectionSearch(lastQuery)
            }
        }
    }
    fun collectionSearch(query: String) {
        val validQuery = query.trim()
        lastQuery = validQuery
        if (validQuery.isBlank()) {
            _collectionItems.value = _allCollection.value
            return
            }
        _collectionItems.value = _allCollection.value.filter { item ->
            item.title.contains(validQuery, ignoreCase = true)
        }
    }
}

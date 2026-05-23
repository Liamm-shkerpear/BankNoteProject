package com.example.banknoteproject.ui.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CollectionViewModel(private val collection: CollectionDao) : ViewModel() {
    val collectionItems : StateFlow<List<BanknoteItem>> = collection.getAllCollection().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}

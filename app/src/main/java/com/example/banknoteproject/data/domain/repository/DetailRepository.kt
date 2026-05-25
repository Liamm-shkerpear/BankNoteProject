package com.example.banknoteproject.data.domain.repository

import com.example.banknoteproject.data.domain.entities.BanknoteItem
import retrofit2.Response

interface DetailRepository {
    suspend fun getItemDetails(id: String, debug: Boolean = true) : Response<BanknoteItem>
}

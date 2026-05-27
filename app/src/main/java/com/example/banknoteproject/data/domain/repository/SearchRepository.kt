package com.example.banknoteproject.data.domain.repository

import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import retrofit2.Response


interface SearchRepository {
    suspend fun searchItems(title: String, page: Int, debug: Boolean = true) : Response<BanknoteResponse>
    suspend fun getAllItems(page: Int, debug: Boolean = true) : Response<BanknoteResponse>
}

package com.example.banknoteproject.data.domain.repository

import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun getRecentData(page: Int = 0, debug: Boolean = true) : Response<BanknoteResponse>
}

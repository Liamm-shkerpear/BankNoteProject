package com.example.banknoteproject.data.repository

import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import com.example.banknoteproject.data.domain.repository.HomeRepository
import com.example.banknoteproject.data.source.remote.BanknoteApi
import retrofit2.Response

class HomeRepositoryImpl (
 private val api: BanknoteApi
) : HomeRepository {
    override suspend fun getRecentData(page: Int, debug: Boolean): Response<BanknoteResponse> {
        return api.getRecentItems(page, debug)
    }
}

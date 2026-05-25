package com.example.banknoteproject.data.repository

import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import com.example.banknoteproject.data.domain.repository.SearchRepository
import com.example.banknoteproject.data.source.remote.BanknoteApi
import retrofit2.Response

class SearchRepositoryImpl(private val api: BanknoteApi) : SearchRepository {
    override suspend fun searchItems(
        title: String,
        page: Int,
        debug: Boolean
    ): Response<BanknoteResponse> {
        return api.searchItems(title, page, debug)
    }

    override suspend fun getAllItems(page: Int, debug: Boolean): Response<BanknoteResponse> {
        return api.getAllItems(page, debug)
    }
}

package com.example.banknoteproject.data.repository

import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.repository.DetailRepository
import com.example.banknoteproject.data.source.remote.BanknoteApi
import retrofit2.Response

class DetailRepositoryImpl(private val api: BanknoteApi) : DetailRepository {
    override suspend fun getItemDetails(id: String, debug: Boolean): Response<BanknoteItem> {
        return api.getItemDetails(id, debug)
    }
}

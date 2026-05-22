package com.example.banknoteproject.data.source.remote


import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BanknoteApi {
    @GET("banknotes")
    suspend fun getRecentItems(
        @Query("page") page: Int = 0,
        @Query("debug") debug: Boolean = true
    ) : Response<BanknoteResponse>
}


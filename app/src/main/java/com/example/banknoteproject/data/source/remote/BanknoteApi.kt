package com.example.banknoteproject.data.source.remote


import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.domain.entities.BanknoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BanknoteApi {
    @GET("banknotes")
    suspend fun getRecentItems(
        @Query("page") page: Int = 0,
        @Query("debug") debug: Boolean = true
    ): Response<BanknoteResponse>

    @GET("banknotes")
    suspend fun getAllItems(
        @Query("page") page: Int,
        @Query("debug") debug: Boolean = true
    ): Response<BanknoteResponse>

    @GET("banknotes/{id}")
    suspend fun getItemDetails(
        @Path("id") id: String,
        @Query("debug") debug: Boolean = true
    ): Response<BanknoteItem>

    @GET("banknotes-search")
    suspend fun searchItems(
        @Query("title") title: String,
        @Query("page") page: Int = 0,
        @Query("debug") debug: Boolean = true
    ): Response<BanknoteResponse>
}


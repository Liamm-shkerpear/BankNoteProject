package com.example.banknoteproject.di

import com.example.banknoteproject.di.module.databaseModule
import com.example.banknoteproject.di.module.repositoryModule
import com.example.banknoteproject.di.module.viewModelModule

val appModule = listOf(
    databaseModule,
    repositoryModule,
    viewModelModule
)

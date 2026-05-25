package com.example.banknoteproject.di.module

import com.example.banknoteproject.data.domain.repository.DetailRepository
import com.example.banknoteproject.data.domain.repository.HomeRepository
import com.example.banknoteproject.data.repository.DetailRepositoryImpl
import com.example.banknoteproject.data.repository.HomeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<DetailRepository> { DetailRepositoryImpl(get()) }
}

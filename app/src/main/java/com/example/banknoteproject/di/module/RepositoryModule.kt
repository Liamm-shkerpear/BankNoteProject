package com.example.banknoteproject.di.module

import com.example.banknoteproject.data.domain.repository.OnboardingRepository
import com.example.banknoteproject.data.repository.OnboardingRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::OnboardingRepositoryImpl) {
        bind<OnboardingRepository>()
    }
}

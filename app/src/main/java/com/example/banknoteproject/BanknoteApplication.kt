package com.example.banknoteproject

import android.app.Application
import com.example.banknoteproject.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class BanknoteApplication : Application(), KoinStartup {
    override fun onKoinStartup() = KoinConfiguration {
        androidLogger()
        androidContext(this@BanknoteApplication)
        modules(appModule)
    }
}

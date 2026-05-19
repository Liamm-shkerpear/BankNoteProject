//package com.example.banknoteproject.di.module
//
//import android.app.Application
//import androidx.room.Room
//import com.example.banknoteproject.data.source.local.database.AppDatabase
//import org.koin.android.ext.koin.androidApplication
//import org.koin.dsl.module
//
//val databaseModule = module {
//    single { provideDatabase(androidApplication()) }
//}
//
//fun provideDatabase(application: Application): AppDatabase {
//    return Room.databaseBuilder(
//        application,
//        AppDatabase::class.java,
//        "app_database"
//    ).build()
//}
//
//
//

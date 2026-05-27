package com.example.banknoteproject.di.module

import android.app.Application
import androidx.room.Room
import com.example.banknoteproject.data.source.local.dao.CollectionDao
import com.example.banknoteproject.data.source.local.database.AppDatabase
import com.example.banknoteproject.utils.AppConstants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideCollectionDao(get()) }
}

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppConstants.APP_DATABASE
    ).build()
}

fun provideCollectionDao(database: AppDatabase): CollectionDao {
    return database.collectionDao()
}




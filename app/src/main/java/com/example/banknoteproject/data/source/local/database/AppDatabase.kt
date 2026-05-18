package com.example.banknoteproject.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.banknoteproject.data.domain.entities.OnboardingQuiz
import com.example.banknoteproject.data.source.local.dao.OnboardingDao
import kotlin.jvm.java

@Database(entities = [OnboardingQuiz::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun onboardingDao() : OnboardingDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

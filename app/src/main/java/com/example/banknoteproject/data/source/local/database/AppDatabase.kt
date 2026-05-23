package com.example.banknoteproject.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import com.example.banknoteproject.data.source.local.converter.BanknoteConverters
import com.example.banknoteproject.data.source.local.dao.CollectionDao

@TypeConverters(BanknoteConverters::class)
@Database(entities = [BanknoteItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun collectionDao() : CollectionDao
}

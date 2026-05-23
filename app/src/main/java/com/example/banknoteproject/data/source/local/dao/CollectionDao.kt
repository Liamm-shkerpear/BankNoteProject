package com.example.banknoteproject.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.banknoteproject.data.domain.entities.BanknoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {
    @Query("SELECT * FROM banknote_item")
    fun getAllCollection(): Flow<List<BanknoteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollection(itemId: BanknoteItem)

    @Query("DELETE FROM banknote_item WHERE id = :itemId")
    suspend fun deleteCollection(itemId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM banknote_item WHERE id = :itemId)")
    fun isCollection(itemId: String): Flow<Boolean>

}

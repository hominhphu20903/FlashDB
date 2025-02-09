package com.phuhm.flashalertdb.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.data.model.FlashType
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashDao {
    @Query("SELECT * FROM Flash")
    fun getFlashes(): Flow<List<Flash>>

    @Query("SELECT * FROM Flash WHERE type = :type")
    fun getFlashByType(type: FlashType): Flash?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlash(flash: Flash): Long

    @Delete
    suspend fun deleteFlash(flash: Flash): Int
}
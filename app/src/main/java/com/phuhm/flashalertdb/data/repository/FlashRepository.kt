package com.phuhm.flashalertdb.data.repository

import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.data.model.FlashType
import kotlinx.coroutines.flow.Flow

interface FlashRepository {
    fun getFlashes(): Flow<List<Flash>>
    fun getFlashByType(type: FlashType): Flash?
    suspend fun insertFlash(flash: Flash): Long
    suspend fun deleteFlash(flash: Flash): Int
}
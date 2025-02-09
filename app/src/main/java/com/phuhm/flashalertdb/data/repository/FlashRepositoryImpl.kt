package com.phuhm.flashalertdb.data.repository

import com.phuhm.flashalertdb.data.dao.FlashDao
import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.data.model.FlashType
import kotlinx.coroutines.flow.Flow

class FlashRepositoryImpl(private val dao: FlashDao) : FlashRepository{
    override fun getFlashes(): Flow<List<Flash>> {
        return dao.getFlashes()
    }

    override fun getFlashByType(type: FlashType): Flash? {
        return dao.getFlashByType(type)
    }

    override suspend fun insertFlash(flash: Flash): Long {
       return dao.insertFlash(flash)
    }

    override suspend fun deleteFlash(flash: Flash): Int {
        return dao.deleteFlash(flash)
    }
}
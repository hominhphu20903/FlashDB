package com.phuhm.flashalertdb.shared

import android.app.Application
import com.phuhm.flashalertdb.data.database.FlashDatabase
import com.phuhm.flashalertdb.data.repository.FlashRepositoryImpl

class MyApplication : Application() {
    companion object {
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            return instance ?: throw IllegalStateException("Application instance not initialized")
        }
    }

    lateinit var flashRepositoryImpl: FlashRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        instance = this

        FlashDatabase.getDatabase(this).openHelper.readableDatabase
        flashRepositoryImpl = FlashRepositoryImpl(FlashDatabase.getDatabase(this).flashDao())
    }
}
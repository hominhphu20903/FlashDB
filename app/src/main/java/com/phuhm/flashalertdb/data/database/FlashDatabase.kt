package com.phuhm.flashalertdb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.phuhm.flashalertdb.data.dao.FlashDao
import com.phuhm.flashalertdb.data.model.Flash
import com.phuhm.flashalertdb.data.model.FlashType
import com.phuhm.flashalertdb.shared.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Flash::class], version = 1, exportSchema = false)
abstract class FlashDatabase : RoomDatabase() {
    abstract fun flashDao(): FlashDao

    companion object {

        @Volatile
        private var INSTANCE: FlashDatabase? = null

        fun getDatabase(context: Context): FlashDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashDatabase::class.java,
                    Constants.DB_NAME
                ).allowMainThreadQueries().fallbackToDestructiveMigration().addCallback(FlashDatabaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class FlashDatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                val flashes = mutableListOf<Flash>()
                flashes.add(Flash(FlashType.LIGHT, 1000, 1000))
                flashes.add(Flash(FlashType.CALLS, 1000, 1000))
                flashes.add(Flash(FlashType.SMS, 1000, 1000))
                flashes.add(Flash(FlashType.NOTIFICATION, 1000, 1000))
                flashes.forEach { flash ->
                    INSTANCE?.flashDao()?.insertFlash(flash)
                }
            }
        }
    }
}
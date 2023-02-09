package com.example.penjualan_muhammadaasad.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [tbBarang::class],
    version = 1
)
abstract class dbPenjualan : RoomDatabase(){

    abstract fun tbPenjualanDAO() : barangDAO

    companion object {

        @Volatile private var instance : dbPenjualan? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            dbPenjualan::class.java,
            "note12345.db"
        ).build()
    }
}
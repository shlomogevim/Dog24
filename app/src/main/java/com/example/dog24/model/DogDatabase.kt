package com.example.dog24.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class),version = 1)
abstract class DogDatabase:RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var instane: DogDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instane ?: synchronized(LOCK) {
            instane ?: buildDataBase(context).also {
                instane = it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}
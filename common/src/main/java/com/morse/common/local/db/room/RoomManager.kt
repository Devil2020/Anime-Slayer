package com.morse.common.local.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.morse.common.BuildConfig


@Database(entities = arrayOf(Entity::class), version =1 ,exportSchema = false )
abstract class RoomManager : RoomDatabase() {

    abstract fun getMovieDao(): Dao

    companion object {
        @Volatile private var instance: RoomManager? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context ,
            RoomManager::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}
package com.iko.android.courier.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class CourierDatabase: RoomDatabase(){
    companion object {

        private var instance: CourierDatabase? = null
        private const val DB_NAME = "Courier.db"

        @Synchronized
        fun getInstance(context: Context): CourierDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, CourierDatabase::class.java, DB_NAME )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addMigrations()
                    .build()
            }
            return instance!!
        }
    }

}
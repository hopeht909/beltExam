package com.example.fragmenttest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 2, exportSchema = false)
abstract class DatabaseShow: RoomDatabase() {


    abstract fun showDao(): DaoShow


    companion object{
        @Volatile
        private var INSTANCE: DatabaseShow? = null

        fun getDatabase(context: Context): DatabaseShow{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseShow::class.java,
                    "note_database" // databaseName
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
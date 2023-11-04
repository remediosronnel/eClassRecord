package com.remedios.eclassrecordteacher.classes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClassData::class], version = 1)
abstract class ClassDatabase :RoomDatabase(){

    abstract fun getClassDao(): ClassDao

    companion object{
        @Volatile
        private var instance: ClassDatabase? = null
        private var LOCK = Any()


        operator fun  invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ClassDatabase::class.java,
            "class-database"
        ).build()
    }

}
package com.remedios.eclassrecordteacher.marks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Marks::class], version = 1)
abstract class MarkDatabase : RoomDatabase() {

    abstract fun getMarkDao() : MarksDao

    companion object{

        @Volatile
        private var instance: MarkDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance=it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MarkDatabase::class.java,
            "mark-database"
        ).build()
    }

}
package com.remedios.eclassrecordteacher.exams

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exams::class], version = 1)
abstract class ExamDatabase : RoomDatabase() {

    abstract fun getExamsDao(): ExamsDao

    companion object{

        @Volatile
        private var instance: ExamDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExamDatabase::class.java,
            "exam-database"
        ).build()

    }

}
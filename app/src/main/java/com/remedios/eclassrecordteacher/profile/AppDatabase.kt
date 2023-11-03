package com.remedios.eclassrecordteacher.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.remedios.eclassrecordteacher.student.AppDatabase

@Database(entities = [TeacherUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTeacherDao(): TeacherDao

    companion object{
        @Volatile
        private var instance: com.remedios.eclassrecordteacher.profile.AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = Companion.instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            com.remedios.eclassrecordteacher.profile.AppDatabase::class.java,
            "teacher-database"
        ).build()
    }

}
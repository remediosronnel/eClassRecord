package com.remedios.eclassrecordteacher.profile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TeacherDao {

    @Insert
    suspend fun addTeacher(teacherUser: TeacherUser)

//    @Query("SELECT * FROM TeacherUser")
//    suspend fun getAllUser(teacherUser: TeacherUser)
    @Update
    suspend fun updateTeacher(teacherUser: TeacherUser)

    @Delete
    suspend fun delete(teacherUser: TeacherUser)



}
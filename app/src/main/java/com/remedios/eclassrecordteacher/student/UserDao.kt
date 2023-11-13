package com.remedios.eclassrecordteacher.student

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery


@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUser():List<User>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

//    @Query("SELECT * FROM user JOIN teacherUser ON user.id = teacherUser.id")
//    suspend fun getAllStudents():Map<User, List<TeacherUser>>
//    fun insertDataRawFormat(query: SupportSQLiteQuery): Boolean?

}
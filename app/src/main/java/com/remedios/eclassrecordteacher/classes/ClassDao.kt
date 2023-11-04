package com.remedios.eclassrecordteacher.classes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClassDao {

    @Insert
    suspend fun addClass(classData: ClassData)

    @Query("SELECT * FROM classdata ORDER by id ASC")
    suspend fun getAllClass(): List<ClassData>

    @Update
    suspend fun updateClass(classData: ClassData)

    @Delete
    suspend fun deleteClass(classData: ClassData)

}
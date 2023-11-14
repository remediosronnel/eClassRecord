package com.remedios.eclassrecordteacher.marks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MarksDao {

    @Insert
    suspend fun addMarks(marks: Marks)

    @Query("SELECT * FROM marks ORDER BY studentName ASC")
    suspend fun getAllMarks():List<Marks>

    @Update
    suspend fun updateMarks(marks: Marks)

    @Delete
    suspend fun deleteMarks(marks: Marks)
}
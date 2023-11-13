package com.remedios.eclassrecordteacher.exams

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExamsDao {

    @Insert
    suspend fun addExams(exams: Exams)

    @Query ("SELECT * FROM exams ORDER BY id ASC")
    suspend fun getAllExams():List<Exams>

    @Update
    suspend fun updateExams(exams: Exams)

    @Delete
    suspend fun deleteExam(exams: Exams)

}
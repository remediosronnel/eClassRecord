package com.remedios.eclassrecordteacher.profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TeacherUser(
    val teacherName: String,
    val schoolName: String,
    val districtName: String,
    val divisionName: String
) : Serializable{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}

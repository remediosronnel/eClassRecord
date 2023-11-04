package com.remedios.eclassrecordteacher.exams

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "item_exams")
data class Exams(
    val name:String = "",
    val schoolName:String = "",
    val districtName:String = "",
    val divisionName:String = "",

):Serializable{

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}

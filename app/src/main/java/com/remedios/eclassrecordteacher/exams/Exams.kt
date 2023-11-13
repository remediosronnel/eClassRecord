package com.remedios.eclassrecordteacher.exams

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Exams(
    val className:String = "",
    val examName:String = "",
    val numberItem:String = "",
    val examDate:String = "",

):Serializable{

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}

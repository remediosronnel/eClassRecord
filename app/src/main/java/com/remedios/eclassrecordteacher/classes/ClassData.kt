package com.remedios.eclassrecordteacher.classes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity()
data class ClassData(
    var className:String,
    var teacherName:String,
    var startDate:String,
    var endDate:String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

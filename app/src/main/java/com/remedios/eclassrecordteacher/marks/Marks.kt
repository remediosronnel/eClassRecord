package com.remedios.eclassrecordteacher.marks

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Marks(
        val studentName:String = "",
        val studentLrn:String = "",
        val studentGender:String = "",
        val examScore:String = ""
):Serializable{
        @PrimaryKey(autoGenerate = true)
        var id:Int = 0
}

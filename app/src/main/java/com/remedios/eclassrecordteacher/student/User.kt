package com.remedios.eclassrecordteacher.student

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class User(
    var studentName: String = "",
    var studentLRN:String = "",
    var birthDate:String = "",
    var enrolledDate:String = "",
    var studentGender:String = "",
    var studentRemarks:String = "",
    var motherName:String = "",
    var fatherName:String = "",
    var studentAddress:String = "",
    var contactNumber:String = ""



): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}
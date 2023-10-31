package com.remedios.eclassrecordteacher.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var firstName:String = "",
    var lastName:String = "",
    var studentNumber:Int = 0,
    var studentLRN:Int = 0,
    var studentGender:String = ""


) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
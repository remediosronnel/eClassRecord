package com.remedios.eclassrecordteacher.db


import androidx.room.PrimaryKey

data class User(
    val userName:String,
    val image:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0

}

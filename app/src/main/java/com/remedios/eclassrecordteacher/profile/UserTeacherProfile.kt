package com.remedios.eclassrecordteacher.profile

import android.media.Image

data class UserTeacherProfile(
    val name:String,
    val schoolName:String,
    val districtName:String,
    val divisionName:String,
    val profilePic: Image,
    val logoPic:Image
)

package com.remedios.eclassrecordteacher.profile

import android.media.Image
import com.sun.mail.imap.protocol.ID

data class UserTeacherProfile(
    val id: String,
    val name:String,
    val schoolName:String,
    val districtName:String,
    val divisionName:String,
    val profilePic: Image,
    val logoPic:Image
)

package com.remedios.eclassrecordteacher.db

import android.view.autofill.AutofillId

data class UserModel(
    val name:String? = "",
    val schoolName:String? = "",
    val districtName:String? = "",
    val divisionName:String? = "",
    val image:String? = ""
)

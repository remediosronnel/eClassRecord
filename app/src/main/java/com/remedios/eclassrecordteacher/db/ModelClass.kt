package com.remedios.eclassrecordteacher.db

import android.graphics.Bitmap

class ModelClass(
    private var name: String,
    private var schoolName: String,
    private var districtName: String,
    private var divisionName: String,
    private var image: Bitmap,
) {



    fun ModelClass(name: String, schoolName: String, districtName:String, divisionName:String, image:Bitmap){
            this.name = name
            this.schoolName = schoolName
            this.districtName = districtName
            this.divisionName = divisionName
            this.image = image
    }

    fun getName():String{
        return name
    }
    fun  setName(name:String){
        this.name = name
    }

    fun getSchoolName():String{
        return schoolName
    }
    fun setSchoolName(schoolName: String){
        this.schoolName = schoolName
    }

    fun getDistrictName() : String{
        return districtName
    }

    fun setDistrictName(districtName: String) {
        this.districtName = districtName
    }

    fun getDivisionName() : String{
        return divisionName
    }

    fun setDivisionName(divisionName: String){
        this.divisionName = divisionName
    }

    fun getImage():Bitmap{
        return image
    }

    fun setImage(image: Bitmap){
        this.image = image
    }


}
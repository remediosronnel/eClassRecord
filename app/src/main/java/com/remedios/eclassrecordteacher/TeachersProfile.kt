package com.remedios.eclassrecordteacher

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle

import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.remedios.eclassrecordteacher.databinding.ActivityTeachersProfileBinding
import com.remedios.eclassrecordteacher.db.DBHelper
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class TeachersProfile : AppCompatActivity() {
    private lateinit var binding: ActivityTeachersProfileBinding
    private var uri:Uri? = null
    private var bitmapImage:Bitmap? = null
    private lateinit var dbHelper:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}
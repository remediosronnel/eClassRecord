package com.remedios.eclassrecordteacher

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
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
import com.remedios.eclassrecordteacher.db.MyDbHelper
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class TeachersProfile : AppCompatActivity(){

private lateinit var binding: ActivityTeachersProfileBinding
    private val CAMERA_REQUEST_CODE = 100
    private val STORAGE_REQUEST_CODE = 101
    private val IMAGE_PICK_CAMERA_CODE = 102
    private val IMAGE_PICK_GALLERY_CODE = 103

    private lateinit var cameraPersmission:Array<String>
    private lateinit var storagePermission:Array<String>

    private var imageUri:Uri? = null
    private var name:String? = ""
    private var schoolName:String? = ""
    private var districtName:String? = ""
    private var divisionName:String? = ""
    private var actionBar: ActionBar? = null
    lateinit var dbHelper: MyDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val schoolLogo = findViewById<ImageView>(R.id.school_logo)
        val profilePic = findViewById<ImageView>(R.id.profile_pic)
        val saveButton = findViewById<Button>(R.id.profile_save)

        actionBar = supportActionBar
        actionBar!!.title = "Profile"

        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = MyDbHelper(this)

        cameraPersmission = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        storagePermission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        profilePic.setOnClickListener{
            imagePickDialog()
        }
        schoolLogo.setOnClickListener{
            imagePickDialog()
        }
        saveButton.setOnClickListener {
            inputData()
        }


    }
    private fun inputData(){
        name = "" + binding.teacherName.text.toString().trim()
        schoolName = "" + binding.schoolName.text.toString().trim()
        districtName = "" + binding.districtName.text.toString().trim()
        divisionName = "" + binding.divisionName.text.toString().trim()

        val timestamp = System.currentTimeMillis()
        val id = dbHelper.insertRecord(
            "" + name,
            "" + imageUri,
            "" + schoolName,
            "" + districtName,
            "" + divisionName,
            "" + timestamp,
            "" + timestamp
        )
        Toast.makeText(this, "Record Added against ID $id", Toast.LENGTH_SHORT).show()
    }

    private fun imagePickDialog(){
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Pick Image From")

        builder.setItems(options){dialog, which ->
            if (which==0){
                if(!checkCameraPermission()){
                    requestCameraPermission()
                }else{
                    pickFromCamera()
                }
            }else{
                if (!checkStoragePermission()){
                    requestStoragePermission()
                }else{
                    pickFromGallery()
                }

            }
        }
        builder.show()
    }

    private fun pickFromCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image Title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(
            cameraIntent, IMAGE_PICK_CAMERA_CODE
        )

    }
    private fun pickFromGallery(){
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(
            galleryIntent, IMAGE_PICK_GALLERY_CODE
        )
    }

    private fun requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE)
    }
    private fun checkStoragePermission():Boolean{


        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPersmission, CAMERA_REQUEST_CODE)

    }

    private fun checkCameraPermission(): Boolean{
        val results = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val result1 = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        return results && result1

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isNotEmpty()){
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera()
                    }else{
                        Toast.makeText(this, "Camera and Storage permissions are required", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            STORAGE_REQUEST_CODE -> {
                if(grantResults.isNotEmpty()){
                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storageAccepted){
                        pickFromGallery()
                    }else{
                        Toast.makeText(this, "Storage permission is required", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            if(resultCode == IMAGE_PICK_GALLERY_CODE){
                   CropImage.activity(data!!.data)
                       .setGuidelines(CropImageView.Guidelines.ON)
                       .setAspectRatio(1, 1)
                       .start(this)
            }else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this)
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK){
                    val resultUri = result.uri
                    imageUri = resultUri
                    binding.profilePic.setImageURI(resultUri)
                }
                else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    val error = result.error
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()
                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data)

    }

}
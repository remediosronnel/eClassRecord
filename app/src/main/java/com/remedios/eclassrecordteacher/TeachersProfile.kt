package com.remedios.eclassrecordteacher;

import android.net.Uri
import android.os.Bundle;
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity;
import com.remedios.eclassrecordteacher.databinding.ActivityTeachersProfileBinding
import com.remedios.eclassrecordteacher.db.Config


public class TeachersProfile: AppCompatActivity() {
    lateinit var binding: ActivityTeachersProfileBinding

    private var imageUri: Uri? = null

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it
    binding.profilePic.setImageURI(imageUri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root);

        binding.profilePic.setOnClickListener{
            selectImage.launch("image/*")
        }

        binding.profileSave.setOnClickListener {
            validateData()
        }
    }
    private fun validateData(){
        if(binding.teacherName.text.toString().isEmpty() ||
            binding.schoolName.text.toString().isEmpty() ||
            binding.districtName.text.toString().isEmpty() ||
            binding.divisionName.text.toString().isEmpty() ||
            imageUri == null){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()

        }else {
            uploadImage()
        }
    }
    private fun uploadImage(){
        Config.showDialog(this)
    }
}
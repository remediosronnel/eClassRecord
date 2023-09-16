package com.remedios.eclassrecordteacher;

import android.content.Intent
import android.net.Uri
import android.os.Bundle;
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.remedios.eclassrecordteacher.databinding.ActivityTeachersProfileBinding
import com.remedios.eclassrecordteacher.db.Config
import com.remedios.eclassrecordteacher.db.Config.hideDialog
import com.remedios.eclassrecordteacher.db.UserModel


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

        val storageRef = FirebaseStorage.getInstance().getReference("profile")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .child("profile.jpg")


        storageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    storeData(it)
                }.addOnFailureListener {
                    hideDialog()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                hideDialog()
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun storeData(imageUrl: Uri?) {
        val data = UserModel(
            name = binding.teacherName.text.toString(),
            schoolName = binding.schoolName.text.toString(),
            districtName = binding.divisionName.text.toString(),
            divisionName = binding.divisionName.text.toString(),
            image = imageUrl.toString()
        )

        FirebaseDatabase.getInstance().getReference("users")
            .child(FirebaseAuth.getInstance().currentUser!!.displayName!!)
            .setValue(data).addOnCompleteListener {
                hideDialog()
                if(it.isSuccessful){

                        startActivity(Intent(this, NewActivity::class.java))
                        finish()
//                        Toast.makeText(this, "User register successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }

            }
    }
}
package com.remedios.eclassrecordteacher;

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle;
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.remedios.eclassrecordteacher.databinding.ActivityTeachersProfileBinding
import com.remedios.eclassrecordteacher.db.UserModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import java.io.ByteArrayOutputStream
import kotlin.system.exitProcess


public class TeachersProfile: AppCompatActivity() {
    private lateinit var binding: ActivityTeachersProfileBinding

    private var uri: Uri? = null
    private var sImage:String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersProfileBinding.inflate(layoutInflater)
        setContentView(binding.root);





                binding.profilePic.setOnClickListener {

                    insertImage()

                }
                binding.profileSave.setOnClickListener {
                    insertData()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finishAffinity()

                }


    }
    private fun insertData() {

        val name = binding.teacherName.text.toString()
        val schoolName = binding.schoolName.text.toString()
        val districtName = binding.districtName.text.toString()
        val divisionName = binding.divisionName.text.toString()


        if (name.isNotEmpty() && schoolName.isNotEmpty() && districtName.isNotEmpty() && divisionName.isNotEmpty() && sImage?.isNotEmpty() == true) {




//
        }


        else{

          Toast.makeText(this, "Please fill the empty fields", Toast.LENGTH_SHORT).show() }
    }


    private fun insertImage() {
        var myfileIntent = Intent(Intent.ACTION_GET_CONTENT)
        myfileIntent.type = "image/*"
        ActivityResultLauncher.launch(myfileIntent)

    }

        private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                uri = result.data!!.data!!
                try {
                    val inputStream = contentResolver.openInputStream(uri!!)
                    val myBitmap = BitmapFactory.decodeStream(inputStream)
                    val stream = ByteArrayOutputStream()
                    myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val bytes = stream.toByteArray()
                    sImage = Base64.encodeToString(bytes, Base64.DEFAULT)
                    binding.profilePic.setImageBitmap(myBitmap)
                    inputStream!!.close()
                    Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()


                } catch (ex: Exception) {
                    Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }



}
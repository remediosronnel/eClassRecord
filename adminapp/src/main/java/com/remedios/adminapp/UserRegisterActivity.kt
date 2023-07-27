package com.remedios.adminapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.remedios.adminapp.databinding.ActivityUserRegisterBinding

class UserRegisterActivity: AppCompatActivity() {

    private lateinit var binding:ActivityUserRegisterBinding
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnAdd.setOnClickListener {
            val email = binding.tvAccountName.text.toString()
            val pass = binding.tvPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this, "Empty Fields are not Allowed!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
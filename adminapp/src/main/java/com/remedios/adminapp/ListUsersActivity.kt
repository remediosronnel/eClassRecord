package com.remedios.adminapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.remedios.adminapp.databinding.ActivityListUsersBinding

class ListUsersActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding:ActivityListUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}
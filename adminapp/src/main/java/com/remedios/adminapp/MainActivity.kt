package com.remedios.adminapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.remedios.adminapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.tvUsername1.text.toString()
        val password = binding.tvPassword1.text.toString()

        binding.loginbtn.setOnClickListener {
            if ((username.isEmpty()) && (password.isEmpty())) {

                val intent = Intent(this, UserRegisterActivity::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, "Invalid Entry!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
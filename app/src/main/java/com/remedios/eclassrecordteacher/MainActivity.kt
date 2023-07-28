package com.remedios.eclassrecordteacher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.remedios.eclassrecordteacher.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.logIn.setOnClickListener {
            binding.messageUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.messageUp.setTextColor(resources.getColor(R.color.textColor, null))
            binding.logIn.background = null
            binding.messageLayout.visibility = View.GONE
            binding.logInLayout.visibility = View.VISIBLE
            binding.logIn.setTextColor(resources.getColor(R.color.pinkColor, null))
            binding.LogIn2.visibility = View.VISIBLE
        }

        binding.messageUp.setOnClickListener {
            binding.messageUp.background = null
            binding.messageUp.setTextColor(resources.getColor(R.color.pinkColor, null))
            binding.logIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.messageLayout.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.logIn.setTextColor(resources.getColor(R.color.textColor, null))
            binding.LogIn2.visibility = View.GONE
            binding.btnMsgsend.visibility = View.VISIBLE
        }

        binding.btnMsgsend.setOnClickListener {

            val email = binding.eMailBox.text.toString()
            val subject = "Inquiry"
            val message = binding.messageBox.text.toString()
            val javaMailAPI = JavaMailAPI(this, null, email, subject, message)
            javaMailAPI.execute()


            binding.messageLayout.apply {
                binding.eMailBox.text = null
                binding.messageBox.text = null
            }
        }

        binding.LogIn2.setOnClickListener {

            val email = binding.tvAccountName.text.toString()
            val pass = binding.tvPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this, FirstActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Empty Fields are not Allowed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

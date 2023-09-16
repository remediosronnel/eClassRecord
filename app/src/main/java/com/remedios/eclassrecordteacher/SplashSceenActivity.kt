package com.remedios.eclassrecordteacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class SplashSceenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_sceen)

        val user = FirebaseAuth.getInstance().currentUser
        Handler(Looper.getMainLooper()).postDelayed({
            if(user == null){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                startActivity(Intent(this, NewActivity::class.java))
                finish()
            }

        }, 2000)

    }
}
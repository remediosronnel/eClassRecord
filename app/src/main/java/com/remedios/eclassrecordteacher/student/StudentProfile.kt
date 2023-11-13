package com.remedios.eclassrecordteacher.student

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remedios.eclassrecordteacher.databinding.StudentProfileBinding



@Suppress("DEPRECATION")
class StudentProfile:AppCompatActivity() {
    lateinit var binding: StudentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = StudentProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)





    }
}
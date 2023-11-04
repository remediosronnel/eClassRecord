package com.remedios.eclassrecordteacher.exams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remedios.eclassrecordteacher.databinding.ModelExamBinding

class StudentExam:AppCompatActivity() {
    lateinit var binding: ModelExamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ModelExamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
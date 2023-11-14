package com.remedios.eclassrecordteacher.marks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.remedios.eclassrecordteacher.databinding.ActivityAddExamBinding
import com.remedios.eclassrecordteacher.databinding.ActivityAddMarkBinding
import kotlinx.coroutines.launch

class AddMarks : AppCompatActivity(){
        lateinit var binding:ActivityAddMarkBinding
        private var marks : Marks? = null
    override fun onCreate(savedInstanceState:Bundle?){
        binding = ActivityAddMarkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        marks = intent?.getSerializableExtra("Data") as Marks

        if(marks == null){
            binding.btnSaveMark.text = "Add Mark"
        }else{
            binding.addStudentName.setText(marks?.studentName)
            binding.addStudentLrn.setText(marks?.studentLrn)
            binding.addStudentGender.setText(marks?.studentGender)
            binding.addMarkScore.setText(marks?.examScore)

        }

        binding.btnSaveMark.setOnClickListener { addMark() }



    }

    private fun addMark() {
        val studentName = binding.addStudentName.text.toString()
        val studentLrn = binding.addStudentLrn.text.toString()
        val studentGender = binding.addStudentGender.text.toString()
        val examScore = binding.addMarkScore.text.toString()

        lifecycleScope.launch {
            if (marks == null) {
                val mark = Marks(
                    studentName = studentName,
                    studentLrn = studentLrn,
                    studentGender = studentGender,
                    examScore = examScore
                )
                MarkDatabase(this@AddMarks).getMarkDao().addMarks(mark)
                finish()
            }else{
                val u = Marks(studentName, studentLrn, studentGender, examScore)
                u.id = marks?.id ?:0
                MarkDatabase(this@AddMarks).getMarkDao().updateMarks(u)
                finish()
            }
        }
    }

}
package com.remedios.eclassrecordteacher.exams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.remedios.eclassrecordteacher.databinding.ActivityAddExamBinding
import kotlinx.coroutines.launch


class AddExams : AppCompatActivity() {
    private lateinit var binding:ActivityAddExamBinding
    private var exams:Exams? = null
//    private var classData:ClassData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddExamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        exams = intent.getSerializableExtra("Data") as Exams?

        if(exams == null) {binding.btnSaveExam.text = "Add Exam"}
        else{
            binding.btnSaveExam.text = "Update"
            binding.addExamName.setText(exams?.examName.toString())
            binding.addExamItems.setText(exams?.numberItem.toString())
            binding.addExamDate.setText(exams?.examDate.toString())
            binding.addClassName.setText(exams?.className.toString())
        }

        binding.btnSaveExam.setOnClickListener {
            addExam()
        }


    }

    private fun addExam() {
        val className = binding.addClassName.text.toString()
        val examName = binding.addExamName.text.toString()
        val examItem = binding.addExamItems.text.toString()
        val dateExam = binding.addExamDate.text.toString()

        lifecycleScope.launch {
            if(exams == null) {
                val exam = Exams(
                    className = className,
                    examName = examName,
                    numberItem = examItem,
                    examDate = dateExam
                )
                ExamDatabase(this@AddExams).getExamsDao().addExams(exam)
                finish()

            }else{
                val u = Exams(className = className, examName = examName, numberItem = examItem, examDate = dateExam)
                u.id = exams?.id ?: 0
                ExamDatabase(this@AddExams).getExamsDao().updateExams(u).toString()
                finish()
            }
        }
    }
}
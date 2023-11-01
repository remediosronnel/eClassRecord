package com.remedios.eclassrecordteacher.student


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.remedios.eclassrecordteacher.databinding.AddStudentInfoBinding
import kotlinx.coroutines.launch

class AddStudentInfo:AppCompatActivity() {
    private lateinit var binding: AddStudentInfoBinding
    private var user:User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = AddStudentInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = intent.getSerializableExtra("Data") as User


        if (user==null) binding.btnAddOrUpdateStudent.text = "Add Student"
        else {
            binding.btnAddOrUpdateStudent.text = "Update"
            binding.studentName.setText(user?.studentName.toString())
            binding.studentLrn.setText(user?.studentLRN.toString())
            binding.studentBirthdate.setText(user?.birthDate.toString())
            binding.enrolledDate.setText(user?.enrolledDate.toString())
            binding.addStudentGender.setText(user?.studentGender.toString())
            binding.studentRemarks.setText(user?.studentRemarks.toString())
            binding.motherName.setText(user?.motherName.toString())
            binding.fatherName.setText(user?.fatherName.toString())
            binding.studentAddress.setText(user?.studentAddress.toString())
            binding.contactNumber.setText(user?.contactNumber.toString())
        }



        binding.btnAddOrUpdateStudent.setOnClickListener { addUser() }

    }

    private fun addUser() {

        val studentName = binding.studentName.text.toString()
        val studentLrn = binding.studentLrn.text.toString()
        val birthDate = binding.studentBirthdate.text.toString()
        val enrollDate = binding.enrolledDate.text.toString()
        val gender = binding.addStudentGender.text.toString()
        val remarks = binding.studentRemarks.text.toString()
        val motherName = binding.motherName.text.toString()
        val fatherName = binding.fatherName.text.toString()
        val address = binding.studentAddress.text.toString()
        val contactNo = binding.contactNumber.text.toString()

        lifecycleScope.launch {
            if (user == null) {
                val user = User(
                    studentName = studentName,
                    studentLRN = studentLrn,
                    birthDate = birthDate,
                    enrolledDate = enrollDate,
                    studentGender = gender,
                    studentRemarks = remarks,
                    motherName = motherName,
                    fatherName = fatherName,
                    studentAddress = address,
                    contactNumber = contactNo
                )
                AppDatabase(this@AddStudentInfo).getUserDao().addUser(user)
                finish()
            }else{

            }
        }
    }
}


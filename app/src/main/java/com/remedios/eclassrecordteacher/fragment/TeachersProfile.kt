package com.remedios.eclassrecordteacher.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.remedios.eclassrecordteacher.HomeActivity
import com.remedios.eclassrecordteacher.databinding.FragmentTeachersProfileBinding
import com.remedios.eclassrecordteacher.profile.AppDatabase
import com.remedios.eclassrecordteacher.profile.TeacherUser
import kotlinx.coroutines.launch

class TeachersProfile : Fragment() {
    private lateinit var _binding: FragmentTeachersProfileBinding
    private val teacherUser:TeacherUser? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeachersProfileBinding.inflate(inflater, container, false)




        binding.inputName.setText(teacherUser?.teacherName.toString())
        binding.inputDistrictName.setText(teacherUser?.schoolName.toString())
        binding.inputDistrictName.setText(teacherUser?.districtName.toString())
        binding.inputDivisionName.setText(teacherUser?.divisionName.toString())


        binding.updateProfileCancel.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)

        }

        binding.btnTeacherProfile.setOnClickListener {
            lifecycleScope.launch {
                addTeacherUser()
                Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)

        }

        return binding.root



    }

    private suspend fun addTeacherUser(){

        val teacherName = binding.inputName.text.toString()
        val schoolName = binding.inputSchoolName.text.toString()
        val districtName = binding.inputDistrictName.text.toString()
        val divisionName = binding.inputDivisionName.text.toString()

        lifecycleScope.launch {
            if (teacherUser == null) {
                val teacherUser = TeacherUser(
                    teacherName = teacherName,
                    schoolName = schoolName,
                    districtName = districtName,
                    divisionName = divisionName
                )
                AppDatabase(requireContext().applicationContext).getTeacherDao()
                    .addTeacher(teacherUser)
            } else {
                val u = TeacherUser(teacherName, schoolName, districtName, divisionName)
                u.id = teacherUser?.id ?: 0
                AppDatabase(requireContext().applicationContext).getTeacherDao()
                    .addTeacher(teacherUser)
            }

        }
    }

}
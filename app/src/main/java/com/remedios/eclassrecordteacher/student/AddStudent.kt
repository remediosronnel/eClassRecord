package com.remedios.eclassrecordteacher.student

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.FragmentAddclassItemBinding
import com.remedios.eclassrecordteacher.databinding.ListClassItemBinding
import com.remedios.eclassrecordteacher.databinding.ListStudentItemBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment
import kotlinx.coroutines.launch


class AddStudent: AppCompatActivity() {
    private lateinit var binding: ListStudentItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ListStudentItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentInfo::class.java)
            startActivity(intent)

        }
    }
    override fun onResume(){
        super.onResume()

        lifecycleScope.launch {
            val userList = AppDatabase(this@AddStudent).getUserDao().getAllUser()

            binding.studentRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@AddStudent)
                adapter = UserAdapter().apply {
                    setData(userList)
                    setOnActionEditListener {
                        val intent = Intent(this@AddStudent, AddStudentInfo::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }
                }
            }

        }

    }

}
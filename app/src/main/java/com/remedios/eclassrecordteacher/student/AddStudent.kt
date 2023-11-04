package com.remedios.eclassrecordteacher.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.remedios.eclassrecordteacher.databinding.ListStudentItemBinding
import com.remedios.eclassrecordteacher.exams.StudentExam

import kotlinx.coroutines.launch


class AddStudent: AppCompatActivity() {
    private lateinit var binding: ListStudentItemBinding
    private var mAdapter:UserAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ListStudentItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentInfo::class.java)
            startActivity(intent)

        }
    }
    private fun setAdapter(list:List<User>){
        mAdapter?.setData(list)
    }


    override fun onResume(){
        super.onResume()

        lifecycleScope.launch {
            val userList = AppDatabase(this@AddStudent).getUserDao().getAllUser()

            mAdapter = UserAdapter()
            binding.studentRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@AddStudent)
                adapter = mAdapter
                setAdapter(userList)


                    mAdapter?.setOnActionEditListener {
                        val intent = Intent(this@AddStudent, AddStudentInfo::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }
                    mAdapter?.setOnActionDeleteListener {
                       val builder = AlertDialog.Builder(this@AddStudent)
                        builder.setMessage("Are you sure you want to delete?")
                        builder.setPositiveButton("Yes"){p0, p1 ->
                            lifecycleScope.launch {
                                AppDatabase(this@AddStudent).getUserDao().deleteUser(it)
                                val list = AppDatabase(this@AddStudent).getUserDao().getAllUser()
                                setAdapter(list)
                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("No"){p0, p1->
                            p0.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()

                    }
                    mAdapter?.setOnActionProfileListener {
                            val intent = Intent(this@AddStudent, StudentProfile::class.java)
                            startActivity(intent)
                    }

                    mAdapter?.setOnActionExamListener {
                        val intent = Intent(this@AddStudent, StudentExam::class.java)
                        startActivity(intent)
                    }



            }

        }

    }

}
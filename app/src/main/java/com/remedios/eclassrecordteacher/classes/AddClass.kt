package com.remedios.eclassrecordteacher.classes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.AddClassItemBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment
import kotlinx.coroutines.launch


class AddClass: AppCompatActivity() {
    private lateinit var binding:AddClassItemBinding
    private var classData: ClassData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddClassItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        classData = intent!!.getSerializableExtra("Data") as ClassData?


        if(classData == null) binding.btnAddClass.text = "Add Class"
        else{
            binding.btnAddClass.text = "Update Class"
            binding.classNameAddClass.setText(classData?.className.toString())
            binding.classTeacherAddClass.setText(classData?.teacherName.toString())
            binding.classStartDate.setText(classData?.startDate.toString())
            binding.classEndDate.setText(classData?.endDate.toString())
        }

        binding.buttonBack.setOnClickListener {
           val fragment = ClassesFragment()
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.nav_home, fragment)
            fragmentTransaction.addToBackStack("ClassesFragment")
            fragmentTransaction.commit()

        }

        binding.btnAddClass.setOnClickListener { addClassStudent() }


    }

    private fun addClassStudent() {
        val className = binding.classNameAddClass.text.toString()
        val teacherName = binding.classTeacherAddClass.text.toString()
        val startDate = binding.classStartDate.text.toString()
        val endDate = binding.classEndDate.text.toString()

        lifecycleScope.launch {
            if (classData == null) {
                val classData = ClassData(
                    className = className,
                    teacherName = teacherName,
                    startDate = startDate,
                    endDate = endDate
                )
                ClassDatabase(this@AddClass).getClassDao().addClass(classData)
                finish()
            }else{
                val u = ClassData(className, teacherName, startDate, endDate)
                u.id = classData?.id ?: 0
                ClassDatabase(this@AddClass).getClassDao().updateClass(u)
                finish()
            }
        }
    }
}
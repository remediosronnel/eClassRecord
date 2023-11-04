@file:Suppress("UNREACHABLE_CODE")

package com.remedios.eclassrecordteacher.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.remedios.eclassrecordteacher.classes.AddClass
import com.remedios.eclassrecordteacher.classes.ClassAdapter
import com.remedios.eclassrecordteacher.classes.ClassData
import com.remedios.eclassrecordteacher.classes.ClassDatabase
import com.remedios.eclassrecordteacher.databinding.ListClassItemBinding
import com.remedios.eclassrecordteacher.student.AddStudent
import kotlinx.coroutines.launch

class ClassesFragment : Fragment() {
    private var _binding:ListClassItemBinding? = null
    private var mAdapter:ClassAdapter? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ListClassItemBinding.inflate(inflater, container, false)



        binding.addClassButton.setOnClickListener {
            val intent = Intent(context?.applicationContext, AddClass::class.java)
            startActivity(intent)
        }



        return binding.root
        }

    private fun setClassAdapter(list: List<ClassData>){
        mAdapter?.setData(list)
    }
    override fun onResume(){
        super.onResume()

        lifecycleScope.launch {
            var classList = ClassDatabase(requireContext().applicationContext).getClassDao().getAllClass()

            mAdapter = ClassAdapter()
            binding.classRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext().applicationContext)
                adapter = mAdapter
                if (classList != null) {
                    setClassAdapter(classList)
                }

                    mAdapter?.setOnActionEditListener {
                        val intent = Intent(requireContext().applicationContext, AddClass::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)

                    }
                    mAdapter?.setOnActionDeleteListener {
                        val builder = AlertDialog.Builder(context)
                        builder.setMessage("Are you sure you want to delete?")
                        builder.setPositiveButton("YES"){p0, p1 ->
                            lifecycleScope.launch {
                                ClassDatabase(context.applicationContext).getClassDao().deleteClass(it)
                                val list = ClassDatabase(context).getClassDao().getAllClass()
                                setClassAdapter(list)
                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("NO"){p0, p1 ->
                            p0.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }

                    mAdapter?.setOnActionStudentListener {
                        val intent = Intent(context.applicationContext, AddStudent::class.java)
                        startActivity(intent)
                    }
                    mAdapter?.setOnActionExamListener {

                    }

            }
        }

    }




}




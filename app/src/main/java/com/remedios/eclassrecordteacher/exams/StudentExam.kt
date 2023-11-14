package com.remedios.eclassrecordteacher.exams

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.remedios.eclassrecordteacher.databinding.ListExamItemBinding
import com.remedios.eclassrecordteacher.marks.MarksActivity
import kotlinx.coroutines.launch


class StudentExam:AppCompatActivity() {
        private lateinit var binding: ListExamItemBinding
        private var mAdapter:ExamAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ListExamItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addClassButton.setOnClickListener {
            val intent = Intent(this, AddExams::class.java)
            startActivity(intent)

        }
    }
    private fun setAdapter(list:List<Exams>){
        mAdapter?.setData(list)
    }
    override fun onResume(){
        super.onResume()

        lifecycleScope.launch {
            val examList = ExamDatabase(this@StudentExam).getExamsDao().getAllExams()

            mAdapter = ExamAdapter()
            binding.examRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@StudentExam)
                adapter = mAdapter
                setAdapter(examList)


                    mAdapter?.setOnActionEditListener {
                        val intent = Intent(this@StudentExam, AddExams::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }
                    mAdapter?.setOnActionDeleteListener {
                        val builder = AlertDialog.Builder(this@StudentExam)
                        builder.setMessage("Are you sure you want to delete?")
                        builder.setPositiveButton("YES"){p0, p1 ->
                            lifecycleScope.launch {
                                ExamDatabase(this@StudentExam).getExamsDao().deleteExam(it)
                                val list = ExamDatabase(this@StudentExam).getExamsDao().getAllExams()
                                setAdapter(list)
                            }
                            p0.dismiss()
                        }
                        builder.setNegativeButton("NO"){p0, p1 ->
                            p0.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()
                    }
                    mAdapter?.setOnActionMarkListener {
                        val intent = Intent(this@StudentExam, MarksActivity::class.java)
                        startActivity(intent)

                    }
            }


        }

    }

}
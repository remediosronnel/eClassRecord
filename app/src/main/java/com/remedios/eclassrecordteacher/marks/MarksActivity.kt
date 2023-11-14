package com.remedios.eclassrecordteacher.marks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.ActivityMarksBinding
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class MarksActivity : AppCompatActivity() {
    lateinit var binding:ActivityMarksBinding
    private var mAdapter : MarkAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMarksBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addMarkButton.setOnClickListener {
            val intent = Intent(this@MarksActivity, AddMarks::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapter(list:List<Marks>){
        mAdapter?.setData(list)

    }

    override fun onResume(){
        super.onResume()

        lifecycleScope.launch {
            val markList = MarkDatabase(this@MarksActivity).getMarkDao().getAllMarks()

            mAdapter = MarkAdapter()
            binding.markRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MarksActivity)
                adapter = mAdapter
                setAdapter(markList)


                mAdapter?.setOnActionEditListener {
                        val intent = Intent(this@MarksActivity, AddMarks::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }

                    mAdapter?.setOnActionDeleteListener {
                        val builder = AlertDialog.Builder(this@MarksActivity)
                        builder.setMessage("Are you sure you want to delete this?")
                        builder.setPositiveButton("Yes"){p0, p1 ->
                            lifecycleScope.launch {
                                MarkDatabase(this@MarksActivity).getMarkDao().deleteMarks(it)
                                val list = MarkDatabase(this@MarksActivity).getMarkDao().getAllMarks()
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
                mAdapter?.setOnActionExcuseListener {
                    val actionPresent:FrameLayout = findViewById(R.id.frame_present)
                    val actionAbsent:FrameLayout = findViewById(R.id.frame_absent)
                    val actionExcuse:FrameLayout = findViewById(R.id.frame_excuse)

                    actionPresent.setOnClickListener { View.VISIBLE }
                    actionAbsent.setOnClickListener { View.GONE }
                    actionExcuse.setOnClickListener { View.GONE }

                }
                mAdapter?.setOnActionPresentListener {
                    val actionPresent:FrameLayout = findViewById(R.id.frame_present)
                    val actionAbsent:FrameLayout = findViewById(R.id.frame_absent)
                    val actionExcuse:FrameLayout = findViewById(R.id.frame_excuse)

                    actionPresent.setOnClickListener { View.GONE }
                    actionAbsent.setOnClickListener { View.VISIBLE }
                    actionExcuse.setOnClickListener { View.GONE }

                }
                mAdapter?.setOnActionAbsentListener {
                    val actionPresent:FrameLayout = findViewById(R.id.frame_present)
                    val actionAbsent:FrameLayout = findViewById(R.id.frame_absent)
                    val actionExcuse:FrameLayout = findViewById(R.id.frame_excuse)
                    actionPresent.setOnClickListener { View.GONE }
                    actionAbsent.setOnClickListener { View.GONE }
                    actionExcuse.setOnClickListener { View.VISIBLE }
                }

                }
            }
        }


}
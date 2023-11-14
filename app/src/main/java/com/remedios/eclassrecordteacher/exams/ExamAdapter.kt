package com.remedios.eclassrecordteacher.exams

import android.content.ComponentCallbacks
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.classes.ClassData
import com.remedios.eclassrecordteacher.classes.ClassDatabase
import kotlin.coroutines.coroutineContext

class ExamAdapter: RecyclerView.Adapter<ExamAdapter.ExamViewHolder>() {
    private var list = mutableListOf<Exams>()
    private var actionEdit: ((Exams) -> Unit)? = null
    private var actionDelete: ((Exams) -> Unit)? = null
    private var actionMark: ((Exams) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.model_exam, parent, false)

        return ExamViewHolder(view)
    }

    override fun getItemCount() = list.size



    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
            val exam = list[position]
        holder.examName.text = exam.examName
        holder.examItems.text = exam.numberItem
        holder.examDate.text = exam.examDate

        holder.actionEdit.setOnClickListener{actionEdit?.invoke(exam)}
        holder.actionDelete.setOnClickListener{actionDelete?.invoke(exam)}
        holder.actionMark.setOnClickListener { actionMark?.invoke(exam) }
    }

    fun setData(data:List<Exams>){
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionMarkListener(callback: (Exams) -> Unit){
        this.actionMark = callback
    }

    fun setOnActionEditListener(callback: (Exams) -> Unit){
        this.actionEdit = callback
    }
    fun setOnActionDeleteListener(callback: (Exams) -> Unit){
        this.actionDelete = callback
    }

    class ExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val examName:TextView = itemView.findViewById(R.id.marks_exam_name)
        val examItems:TextView = itemView.findViewById(R.id.no_items)
        val examDate:TextView = itemView.findViewById(R.id.date_exam)

        val actionMark:ImageView = itemView.findViewById(R.id.iv_mark)
        val actionEdit:ImageView = itemView.findViewById(R.id.exam_edit)
        val actionDelete:ImageView = itemView.findViewById(R.id.exam_delete)

    }
}
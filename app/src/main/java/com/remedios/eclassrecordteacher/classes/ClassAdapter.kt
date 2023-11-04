package com.remedios.eclassrecordteacher.classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R


class ClassAdapter : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {
    private var list = mutableListOf<ClassData>()
    private var actionEdit:((ClassData) -> Unit)? = null
    private var actionDelete:((ClassData) -> Unit)? = null
    private var actionExam:((ClassData) -> Unit)? = null
    private var actionStudent:((ClassData) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.model_class, parent, false)

        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classData = list[position]
        holder.className.text = classData.className
        holder.teacherName.text = classData.teacherName
        holder.startDate.text = classData.startDate
        holder.endDate.text = classData.endDate

        holder.actionEdit.setOnClickListener{actionEdit?.invoke(classData)}
        holder.actionDelete.setOnClickListener{actionDelete?.invoke(classData)}
        holder.actionExam.setOnClickListener{actionExam?.invoke(classData)}
        holder.actionStudent.setOnClickListener{actionStudent?.invoke(classData)}
    }

    override fun getItemCount() = list.size

    fun setData(data: List<ClassData>){
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (ClassData) -> Unit){
        this.actionEdit = callback
    }
    fun setOnActionDeleteListener(callback: (ClassData) -> Unit){
        this.actionDelete = callback
    }
    fun setOnActionExamListener(callback: (ClassData) -> Unit){
        this.actionExam = callback
    }
    fun setOnActionStudentListener(callback: (ClassData) -> Unit){
        this.actionStudent = callback
    }



    class ClassViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val className:TextView = itemView.findViewById(R.id.classes_name)
        val teacherName:TextView = itemView.findViewById(R.id.teacher_incharge)
        val startDate:TextView = itemView.findViewById(R.id.classStartDate)
        val endDate:TextView = itemView.findViewById(R.id.classEndDate)

        val actionEdit:ImageView = itemView.findViewById(R.id.class_edit)
        val actionDelete:ImageView = itemView.findViewById(R.id.class_delete)
        val actionExam:ImageView = itemView.findViewById(R.id.class_exam)
        val actionStudent:ImageView = itemView.findViewById(R.id.class_addStudent)

    }


}
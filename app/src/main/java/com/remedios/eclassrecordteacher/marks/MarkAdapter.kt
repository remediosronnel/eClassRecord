package com.remedios.eclassrecordteacher.marks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R

class MarkAdapter : RecyclerView.Adapter<MarkAdapter.MarkViewHolder>() {

    private var list = mutableListOf<Marks>()
    private var actionEdit:((Marks) -> Unit)? = null
    private var actionDelete:((Marks) -> Unit)? = null
    private var actionExcuse:((Marks) -> Unit)? = null
    private var actionAbsent:((Marks) -> Unit)? = null
    private var actionPresent:((Marks) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkViewHolder {
        val view =
            LayoutInflater.from(parent.context)
            .inflate(R.layout.edit_exam, parent, false)

        return MarkViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MarkViewHolder, position: Int) {
       val marks = list[position]
        holder.studentID.text = marks.id.toString()
        holder.studentName.text = marks.studentName
        holder.studentLrn.text = marks.studentLrn
        holder.studentGender.text = marks.studentGender
        holder.examScore.text = marks.examScore

        holder.actionEdit.setOnClickListener{actionEdit?.invoke(marks)}
        holder.actionDelete.setOnClickListener{actionDelete?.invoke(marks)}
        holder.actionExcuse.setOnClickListener{actionExcuse?.invoke(marks)}
        holder.actionAbsent.setOnClickListener{actionAbsent?.invoke(marks)}
        holder.actionPresent.setOnClickListener { actionPresent?.invoke(marks) }

    }

    fun setData(data:List<Marks>){
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (Marks) -> Unit){
        this.actionEdit = callback
    }
    fun setOnActionDeleteListener(callback: (Marks) -> Unit){
        this.actionDelete = callback
    }
    fun setOnActionExcuseListener(callback: (Marks) -> Unit){
        this.actionExcuse = callback
    }
    fun setOnActionAbsentListener(callback: (Marks) -> Unit){
        this.actionExcuse = callback
    }
    fun setOnActionPresentListener(callback: (Marks) -> Unit){
        this.actionExcuse = callback
    }

    class MarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentID :TextView = itemView.findViewById(R.id.mark_student_id)
        val studentName: TextView = itemView.findViewById(R.id.mark_student_name)
        val studentLrn:TextView = itemView.findViewById(R.id.mark_student_lrn)
        val studentGender:TextView = itemView.findViewById(R.id.mark_student_gender)
        val examScore:TextView = itemView.findViewById(R.id.mark_exam_score)


        val actionEdit:FrameLayout = itemView.findViewById(R.id.frame_score)
        val actionDelete:ImageView = itemView.findViewById(R.id.score_delete)

        val actionExcuse:FrameLayout = itemView.findViewById(R.id.frame_excuse)
        val actionAbsent:FrameLayout = itemView.findViewById(R.id.frame_absent)
        val actionPresent:FrameLayout = itemView.findViewById(R.id.frame_present)

        val imageExcuse:ImageView = itemView.findViewById(R.id.image_excuse)
        val textExcuse:TextView = itemView.findViewById(R.id.text_excuse)


    }

}
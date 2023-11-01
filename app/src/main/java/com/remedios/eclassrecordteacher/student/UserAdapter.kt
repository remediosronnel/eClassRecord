package com.remedios.eclassrecordteacher.student

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R



class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var list = mutableListOf<User>()
    private var actionDelete:((User) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.model_student, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = list[position]
        holder.studentName.text = user.studentName
        holder.studentNumber.text = user.id.toString()
        holder.studentGender.text = user.studentGender
        holder.studentLRN.text = user.studentLRN


        holder.actionDelete.setOnClickListener{actionDelete?.invoke(user)}
    }

    override fun getItemCount() = list.size

    fun setData(data: List<User>){
        list.apply {
            clear()
            addAll(data)
        }
    }

fun  setOnActionEditListener(callback: (User) -> Unit){
    this.actionDelete = callback
}
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentName = itemView.findViewById<TextView>(R.id.tv_studentName)
        val studentLRN = itemView.findViewById<TextView>(R.id.tv_studentLRN)
        val studentGender = itemView.findViewById<TextView>(R.id.tvGender)
        val studentNumber = itemView.findViewById<TextView>(R.id.tvStudentNumber)
        val actionEdit:ImageView = itemView.findViewById(R.id.student_edit)
        val actionDelete:ImageView = itemView.findViewById(R.id.student_delete)
    }
}
package com.remedios.eclassrecordteacher.classes


import android.content.Context
import android.content.pm.PackageManager.Property
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.remedios.eclassrecordteacher.R


class UserAdapter(val c:Context, private val userList: ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(), View.OnClickListener{
    val itemClickListener: OnItemClickListener? = null

    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        val className: TextView = v.findViewById<TextView>(R.id.classes_name)
        val teacherName: TextView = v.findViewById<TextView>(R.id.teacher_incharge)



        fun bind(itemView: View, itemClickListener: OnItemClickListener){

            val delete:Button = v.findViewById(R.id.frame_deleteClass)
            val student:Button = v.findViewById(R.id.frame_studentsClass)
            val exams:Button = v.findViewById(R.id.frame_examsClass)
            val edit:Button = v.findViewById(R.id.frame_editClass)



        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.model_class, parent, false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]

        holder.className.text = newList.className
        holder.teacherName.text = newList.teacherName


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
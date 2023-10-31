package com.remedios.eclassrecordteacher.classes


import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivities
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startForegroundService
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.MainActivity
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.TeachersProfile
import com.remedios.eclassrecordteacher.databinding.ModelClassBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment
import com.remedios.eclassrecordteacher.student.AddStudent
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


class UserAdapter(val c:Context, private val userList: ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){




    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){

        var className: TextView
        var teacherName: TextView
        var startDate:TextView
        var endDate:TextView
        var mMenus:Button

        init {
            className = v.findViewById<TextView>(R.id.classes_name)
            teacherName = v.findViewById<TextView>(R.id.teacher_incharge)
            startDate = v.findViewById<TextView>(R.id.classStartDate)
            endDate = v.findViewById<TextView>(R.id.classEndDate)
            mMenus = v.findViewById(R.id.button_menu)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText ->{
                        val v = LayoutInflater.from(c).inflate(R.layout.fragment_addclass_item, null)
                        val className = v.findViewById<EditText>(R.id.class_nameAddClass)
                        val teacherName = v.findViewById<EditText>(R.id.class_teacherAddClass)
                        val startDate = v.findViewById<EditText>(R.id.class_start_date)
                        val endDate = v.findViewById<EditText>(R.id.class_end_date)

                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                dialog, _ ->
                                position.className = className.text.toString()
                                position.teacherName = teacherName.text.toString()
                                position.startDate = startDate.text.toString()
                                position.endDate = endDate.text.toString()

                                notifyDataSetChanged()
                                Toast.makeText(c, "Information is updated", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete ->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_delete)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes"){
                                dialog, _ ->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Information Deleted", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }

                            .setNegativeButton("No"){
                                dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()



                        true
                    }
                    R.id.addStudent ->{
                        AlertDialog.Builder(c)
                            .setTitle("Add Student")
                            .setIcon(R.drawable.ic_student)
                            .setMessage("Are you sure you want to Add Student")
                            .setPositiveButton("Yes"){
                                dialog, id ->
                                val intent = Intent(c.applicationContext, AddStudent::class.java)
                                c.startActivity(intent)
                            }
                            .setNegativeButton("Cancel"){
                                dialog,_ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true

                    }
                    R.id.addExam ->{
                        AlertDialog.Builder(c)
                            .setTitle("Add Exam")
                            .setIcon(R.drawable.ic_exam)
                            .setMessage("Are you sure you want to Add Exam")
                            .setPositiveButton("Yes"){
                                    dialog, id ->
                                val intent = Intent(c.applicationContext, AddStudent::class.java)
                                c.startActivity(intent)
                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        Toast.makeText(c, "Delete is Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
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
        holder.startDate.text = newList.startDate
        holder.endDate.text = newList.endDate



    }






}
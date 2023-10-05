package com.remedios.eclassrecordteacher.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R

class UserAdapter(val c:Context, val userList: ArrayList<UserData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        inner class UserViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
            var name: TextView
            var mbNum: TextView
            var mMenus: ImageView

            init {
                name = v.findViewById<TextView>(R.id.classes_name)
                mbNum = v.findViewById<TextView>(R.id.teacher_incharge)
                mMenus = v.findViewById(R.id.frame_editClass)
                mMenus.setOnClickListener { popupMenus(it) }
            }

            private fun popupMenus(v: View) {
                val position = userList[adapterPosition]
                val popupMenus = PopupMenu(c, v)
                popupMenus.inflate(R.menu.show_menu)
                popupMenus.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.editText -> {
                            val v =
                                LayoutInflater.from(c).inflate(R.layout.fragment_add_class, null)
                            val className = v.findViewById<EditText>(R.id.class_nameAddClass)
                            val teacherName = v.findViewById<EditText>(R.id.class_teacherAddClass)
                            AlertDialog.Builder(c)
                                .setView(v)
                                .setPositiveButton("Ok") { dialog, _ ->
                                    position.className = className.text.toString()
                                    position.teacherName = teacherName.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(
                                        c,
                                        "User Information is Edited",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    dialog.dismiss()

                                }
                                .setNegativeButton("Cancel") { dialog, _ ->
                                    dialog.dismiss()

                                }
                                .create()
                                .show()

                            true
                        }

                        R.id.delete -> {
                            /**set delete*/
                            AlertDialog.Builder(c)
                                .setTitle("Delete")
                                .setIcon(R.drawable.ic_warning)
                                .setMessage("Are you sure delete this Information")
                                .setPositiveButton("Yes") { dialog, _ ->
                                    userList.removeAt(adapterPosition)
                                    notifyDataSetChanged()
                                    Toast.makeText(
                                        c,
                                        "Deleted this Information",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("No") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()

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

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val newList = userList[position]
            holder.name.text = newList.className
            holder.mbNum.text = newList.teacherName
        }

        override fun getItemCount(): Int {
            return userList.size
        }


}
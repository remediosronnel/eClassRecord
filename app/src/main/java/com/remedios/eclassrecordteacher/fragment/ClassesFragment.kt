@file:Suppress("UNREACHABLE_CODE")

package com.remedios.eclassrecordteacher.fragment


import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.getColumnIndex
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.classes.UserAdapter
import com.remedios.eclassrecordteacher.classes.UserData
import com.remedios.eclassrecordteacher.databinding.ListClassItemBinding
import kotlin.random.Random

class ClassesFragment : Fragment() {
    private var _binding:ListClassItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var addsBtn:Button
    private lateinit var recv:RecyclerView
    private lateinit var userList:ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    private var menuButton: Button? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ListClassItemBinding.inflate(inflater, container, false)

        userList = ArrayList()
        addsBtn = binding.addClassButton
        recv = binding.classRecyclerView
        userAdapter = UserAdapter(requireContext(), userList)
        recv.layoutManager = LinearLayoutManager(context)
        recv.adapter = userAdapter
        addsBtn!!.setOnClickListener { addInfo() }
        menuButton = binding.root.findViewById(R.id.button_menu)









        return binding.root
        }


    private fun addInfo(){
        val inflter = LayoutInflater.from(context)
        val v = inflter.inflate(R.layout.fragment_addclass_item, null)

        val className = v.findViewById<EditText>(R.id.class_nameAddClass)
        val teacherName = v.findViewById<EditText>(R.id.class_teacherAddClass)
        val addDialog = AlertDialog.Builder(requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog, _->
            val className = className.text.toString()
            val teacherName = teacherName.text.toString()
            userList.add(UserData("$className", "$teacherName", "00-00-00", "00-00-00"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Adding Class Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog, _->
            dialog.dismiss()
            Toast.makeText(context, "Cencel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()

    }


    private fun deleteInfo(){
        val position = userAdapter
        val inflter = LayoutInflater.from(context)
        val v = inflter.inflate(R.layout.fragment_addclass_item, null)

        val className = v.findViewById<EditText>(R.id.class_nameAddClass)
        val teacherName = v.findViewById<EditText>(R.id.class_teacherAddClass)
        val deleteDialog = AlertDialog.Builder(requireContext())

        deleteDialog.setView(v)
        deleteDialog.setPositiveButton("Ok"){
                dialog, _->
            val className = className.text.toString()
            val teacherName = teacherName.text.toString()
            userList.removeAt(position.itemCount)
            userAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Adding Class Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        deleteDialog.setNegativeButton("Cancel"){
                dialog, _->
            dialog.dismiss()
            Toast.makeText(context, "Cencel", Toast.LENGTH_SHORT).show()
        }
        deleteDialog.create()
        deleteDialog.show()

    }

}




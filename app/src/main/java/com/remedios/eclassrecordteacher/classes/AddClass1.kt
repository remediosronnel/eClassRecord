package com.remedios.eclassrecordteacher.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.FragmentAddClassBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment
import com.remedios.eclassrecordteacher.classes.UserDatabase as UserDatabase


class AddClass1 : Fragment(){

    private lateinit var _binding: FragmentAddClassBinding
    private val binding get() = _binding

    private var className: EditText? = null
    private var classTeacher: EditText? = null
    private var startDate:EditText? = null
    private var endDate:EditText? = null
    private lateinit var btnSave:Button
    private lateinit var userDao:UserDao
    private lateinit var userDatabase: UserDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddClassBinding.inflate(inflater, container, false)

        binding.btnCancel.setOnClickListener {
            val transaction:FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container, ClassesFragment())
            transaction.commit()
            transaction.addToBackStack(null)

        }






        btnSave.setOnClickListener {


        }





        return binding.root




    }



}
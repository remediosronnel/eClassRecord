package com.remedios.eclassrecordteacher.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.FragmentAddClassBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment


class AddClass : Fragment(){

    private lateinit var _binding: FragmentAddClassBinding
    private val binding get() = _binding
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






        return binding.root




    }



}
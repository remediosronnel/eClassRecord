@file:Suppress("UNREACHABLE_CODE")

package com.remedios.eclassrecordteacher.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.remedios.eclassrecordteacher.AddClass
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.FragmentClassesBinding

class ClassesFragment : Fragment() {
    private lateinit var _binding:FragmentClassesBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentClassesBinding.inflate(inflater, container, false)

        binding.addClassButton.setOnClickListener {
            val intent = Intent(context, AddClass::class.java)
            startActivity(intent)


        }


        return binding.root




        }


    }




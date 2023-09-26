package com.remedios.eclassrecordteacher.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.remedios.eclassrecordteacher.AddClass
import com.remedios.eclassrecordteacher.HomeActivity
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.databinding.FragmentClassesBinding
import com.remedios.eclassrecordteacher.databinding.FragmentTeachersProfileBinding

class TeachersProfile : Fragment() {
    private lateinit var _binding: FragmentTeachersProfileBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTeachersProfileBinding.inflate(inflater, container, false)


        binding.updateProfileCancel.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)

        }




        return binding.root



    }

}
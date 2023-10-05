@file:Suppress("UNREACHABLE_CODE")

package com.remedios.eclassrecordteacher.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.classes.AddClass
import com.remedios.eclassrecordteacher.classes.UserAdapter
import com.remedios.eclassrecordteacher.databinding.FragmentClassesBinding

class ClassesFragment : Fragment() {
    private lateinit var _binding:FragmentClassesBinding
    private val binding get() = _binding

    private var userAdapter: UserAdapter? = null
    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentClassesBinding.inflate(inflater, container, false)

        binding.addClassButton.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container, AddClass())
            transaction.addToBackStack(null)
            transaction.commit()

        }




        return binding.root

        }



}




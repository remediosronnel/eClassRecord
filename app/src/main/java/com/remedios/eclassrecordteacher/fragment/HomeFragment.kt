package com.remedios.eclassrecordteacher.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.remedios.eclassrecordteacher.R
import com.remedios.eclassrecordteacher.student.AppDatabase
import com.remedios.eclassrecordteacher.student.User



@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {
    var student: List<User>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

        val numberFragment = mutableListOf<AppDatabase>().lastIndex
        val numberDisplay = view?.findViewById<TextView>(R.id.number_students)
        numberDisplay?.text = numberFragment.toString()






    }

}
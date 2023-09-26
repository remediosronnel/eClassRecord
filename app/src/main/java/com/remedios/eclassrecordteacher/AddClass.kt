package com.remedios.eclassrecordteacher


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remedios.eclassrecordteacher.databinding.ActivityAddClassBinding
import com.remedios.eclassrecordteacher.fragment.ClassesFragment

class AddClass : AppCompatActivity() {
    private lateinit var binding:ActivityAddClassBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddClassBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnCancel.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, ClassesFragment())
                addToBackStack(null)
                commit()
            }

        }



    }
//    private fun replaceFragment(fragment:Fragment){
//        val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment)
//        transaction.commit()
//
//    }

}
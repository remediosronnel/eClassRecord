package com.remedios.eclassrecordteacher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.remedios.eclassrecordteacher.databinding.ActivityAddClassBinding
import com.remedios.eclassrecordteacher.databinding.ActivityHomeBinding

class AddClass : AppCompatActivity() {
    lateinit var binding:ActivityAddClassBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddClassBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }


}
package com.remedios.eclassrecordteacher

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView

import android.widget.TextView

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.FirebaseStorage.*

import com.remedios.eclassrecordteacher.databinding.ActivityHomeBinding
import com.remedios.eclassrecordteacher.exportdata.XlsExportActivity
import com.remedios.eclassrecordteacher.fragment.ClassesFragment
import com.remedios.eclassrecordteacher.fragment.HomeFragment
import com.remedios.eclassrecordteacher.fragment.TeachersProfile
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
            private lateinit var drawerLayout:DrawerLayout
            private var imageView: ImageView? = null
            private var teacherName: TextView? = null
            private lateinit var binding:ActivityHomeBinding

            private var storage: FirebaseStorage = getInstance()
            private var storageReference = storage.reference
            private lateinit var databaseReference : DatabaseReference
            private lateinit var auth:FirebaseAuth
            private lateinit var userID:String
            private lateinit var uri:Uri


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityHomeBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val name = intent.getStringExtra("name")
                val pic:Int = intent.getIntExtra("image12", 0)


                drawerLayout = findViewById(R.id.drawer_layout)
                imageView = findViewById(R.id.nav_picture)
                teacherName = binding.navView.findViewById(R.id.nav_name)

                teacherName?.text = name
                imageView?.setImageResource(pic)

                auth = Firebase.auth
                auth.signInAnonymously()


                val toolbar = findViewById<Toolbar>(R.id.toolbar)
                setSupportActionBar(toolbar)

                val navigationView = findViewById<NavigationView>(R.id.nav_view)
                navigationView.setNavigationItemSelectedListener(this)

                val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()

                if(savedInstanceState == null){
                    replaceFragment(HomeFragment())
                    navigationView.setCheckedItem(R.id.nav_home)
                }




            }
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.nav_home -> {
                        replaceFragment(HomeFragment())
                    }
                    R.id.nav_classes -> {
                        replaceFragment(ClassesFragment())
                    }
                    R.id.nav_profile -> {
                        replaceFragment(TeachersProfile())
                    }
                    R.id.nav_save -> {
                            val intent = Intent(this, XlsExportActivity::class.java)
                            startActivity(intent)
                    }

                    R.id.nav_logout -> {
                        exitProcess(0)
                        com.remedios.eclassrecordteacher.TeachersProfile().finish()

                    }

                }
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }


            private fun replaceFragment(fragment:Fragment){
                val transaction:FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
            }

            override fun onBackPressed() {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START)
                }else{
                    onBackPressedDispatcher.onBackPressed()
                }
            }


}
 package com.kilani.nowornever.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kilani.nowornever.R
import com.kilani.nowornever.ui.home.ReceivedChallengesFragment
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         initListeners()
     }

     private fun initListeners() {
         bottomNavigationView.setOnNavigationItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.home -> {

                     return@setOnNavigationItemSelectedListener true
                 }
                 R.id.profile -> {
                     replaceContentFragment(ReceivedChallengesFragment.newInstance())
                     return@setOnNavigationItemSelectedListener true
                 }
                 else -> {
                     return@setOnNavigationItemSelectedListener true
                 }
             }
         }
     }

     private fun replaceContentFragment(fragment: Fragment?) {
         if (fragment != null) {
             supportFragmentManager.beginTransaction()
                 .replace(R.id.fragmentContainer, fragment)
                 .commit()
         }
     }
 }


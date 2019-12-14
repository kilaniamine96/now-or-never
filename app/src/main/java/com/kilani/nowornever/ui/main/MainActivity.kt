 package com.kilani.nowornever.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.kilani.nowornever.R
import com.kilani.nowornever.ui.homechallenges.ConfirmationDialogFragment
import com.kilani.nowornever.ui.homechallenges.HomeChallengesFragment
import com.kilani.nowornever.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


 class MainActivity : AppCompatActivity() {

     val viewModel by viewModel<MainViewModel>()

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         replaceContentFragment(HomeChallengesFragment.newInstance())
         initListeners()
         viewModel.getUser(FirebaseAuth.getInstance().currentUser!!)
     }


     private fun initListeners() {
         bottomNavigationView.setOnNavigationItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.home -> {
                     replaceContentFragment(HomeChallengesFragment.newInstance())
                     return@setOnNavigationItemSelectedListener true
                 }
                 R.id.profile -> {
                     replaceContentFragment(ProfileFragment.newInstance())
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

     fun showConfirmationDialog(onConfirm: () -> Unit) {
         val dialog = ConfirmationDialogFragment.newInstance()
         dialog.show(supportFragmentManager,"ConfirmationDialog")
         dialog.listener = object : ConfirmationDialogFragment.ConfirmationDialogListener {
             override fun onCancel() {
                 dialog.dismiss()
             }
             override fun onConfirm() {
                 onConfirm()
                 dialog.dismiss()
             }
         }
     }
 }


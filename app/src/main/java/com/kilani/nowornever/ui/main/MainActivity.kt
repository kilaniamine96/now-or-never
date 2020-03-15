 package com.kilani.nowornever.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.kilani.nowornever.R
import com.kilani.nowornever.ui.homechallenges.ConfirmationDialogFragment
import com.kilani.nowornever.ui.homechallenges.HomeChallengesFragment
import com.kilani.nowornever.ui.login.LoginActivity
import com.kilani.nowornever.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


 class MainActivity : AppCompatActivity() {

     val viewModel by viewModel<MainViewModel>()
     private val currentUser = FirebaseAuth.getInstance().currentUser!!

     private var savedStateSparseArray = SparseArray<Fragment.SavedState>()
     private var currentSelectItemId = R.id.home
     companion object {
         const val SAVED_STATE_CONTAINER_KEY = "ContainerKey"
         const val SAVED_STATE_CURRENT_TAB_KEY = "CurrentTabKey"
     }

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         replaceContentFragment(HomeChallengesFragment.newInstance())
         initListeners()
         viewModel.getUser(currentUser)
         viewModel.listenForUserUpdates(currentUser)
         viewModel.getAllUsers()
     }


     private fun initListeners() {
         logoutBt.setOnClickListener {
             FirebaseAuth.getInstance().signOut()
             startActivity(Intent(this, LoginActivity::class.java))
             finish()

         }
         bottomNavigationView.setOnNavigationItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.home -> {
                     swapFragments(item.itemId, "Home", HomeChallengesFragment.newInstance())
                     return@setOnNavigationItemSelectedListener true
                 }
                 R.id.profile -> {
                     swapFragments(item.itemId, "Profile", ProfileFragment.newInstance())
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

     override fun onStop() {
         super.onStop()
         viewModel.stopListeningForUpdates()
     }

     override fun onDestroy() {
         super.onDestroy()
         viewModel.stopListeningForUpdates()
     }


     private fun swapFragments(@IdRes actionId: Int, key: String, fragment: Fragment?) {
         if (supportFragmentManager.findFragmentByTag(key) == null) {
             savedFragmentState(actionId)
             createFragment(key, actionId, fragment)
         }
     }

     private fun createFragment(key: String, actionId: Int, fragment: Fragment?) {
         fragment?.setInitialSavedState(savedStateSparseArray[actionId])
         supportFragmentManager.beginTransaction()
             .replace(R.id.fragmentContainer, fragment!!, key)
             .commit()
     }

     private fun savedFragmentState(actionId: Int) {
         val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
         if (currentFragment != null) {
             savedStateSparseArray.put(currentSelectItemId,
                 supportFragmentManager.saveFragmentInstanceState(currentFragment)
             )
         }
         currentSelectItemId = actionId
     }
 }


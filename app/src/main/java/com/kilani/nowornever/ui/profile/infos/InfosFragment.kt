package com.kilani.nowornever.ui.profile.infos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

import com.kilani.nowornever.R
import com.kilani.nowornever.data.stringRes
import com.kilani.nowornever.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_infos.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class InfosFragment : Fragment() {

    val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        fun newInstance() = InfosFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTextViews()
    }

    private fun initTextViews() {
        welcomeUserTv.text = getString(R.string.hello_user_text, viewModel.currentUser.value?.name)
        userLevelTv.text = getString(R.string.user_level_text, getString(viewModel.currentUser.value?.level!!.stringRes()))
    }


}

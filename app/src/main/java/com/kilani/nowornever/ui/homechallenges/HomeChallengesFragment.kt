package com.kilani.nowornever.ui.homechallenges


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

import com.kilani.nowornever.R
import com.kilani.nowornever.ui.homechallenges.current.send.SendChallengeDialogFragment
import kotlinx.android.synthetic.main.fragment_home_challenges.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeChallengesFragment : Fragment() {

    private val viewModel by sharedViewModel<HomeChallengesViewModel>()

    companion object {
        fun newInstance() = HomeChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getChallenges(FirebaseAuth.getInstance().currentUser!!)
        newChallengeFab.setOnClickListener {
            val dialog = SendChallengeDialogFragment.newInstance()
            dialog.show(childFragmentManager, "SendChallengeDialog")
        }
        homeChallengesViewPager.adapter = HomeChallengesPagerAdapter(childFragmentManager)
        challengesTl.setupWithViewPager(homeChallengesViewPager)
    }

}

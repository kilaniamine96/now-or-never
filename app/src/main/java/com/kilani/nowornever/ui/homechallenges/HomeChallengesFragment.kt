package com.kilani.nowornever.ui.homechallenges


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kilani.nowornever.R
import com.kilani.nowornever.ui.homechallenges.current.send.SendChallengeDialogFragment
import kotlinx.android.synthetic.main.fragment_home_challenges.*

/**
 * A simple [Fragment] subclass.
 */
class HomeChallengesFragment : Fragment() {

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
        newChallengeFab.setOnClickListener {
            val dialog = SendChallengeDialogFragment.newInstance()
            dialog.show(childFragmentManager, "HELLO")
        }
        homeChallengesViewPager.adapter = HomeChallengesPagerAdapter(childFragmentManager)
    }

}

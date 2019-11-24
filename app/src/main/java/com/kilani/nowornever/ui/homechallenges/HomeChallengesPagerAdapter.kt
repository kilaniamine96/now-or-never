package com.kilani.nowornever.ui.homechallenges

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kilani.nowornever.ui.homechallenges.current.CurrentChallengesFragment
import com.kilani.nowornever.ui.homechallenges.done.DoneChallengesFragment
import com.kilani.nowornever.ui.homechallenges.received.ReceivedChallengesFragment

class HomeChallengesPagerAdapter(mgr: FragmentManager) : FragmentStatePagerAdapter(mgr, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentsMap = mutableMapOf<Int, Fragment>()

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        fragmentsMap[position] = when (position) {

            0 -> CurrentChallengesFragment.newInstance()
            1 -> ReceivedChallengesFragment.newInstance()
            2 -> DoneChallengesFragment.newInstance()
            else -> CurrentChallengesFragment.newInstance()
        }
        return fragmentsMap[position]!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Défis en cours"
            1 -> "Défis reçus"
            2-> "Défis finis"
            else -> "Défis en cours"
        }
    }

    fun getFragment(position: Int): Fragment = fragmentsMap[position]!!

}
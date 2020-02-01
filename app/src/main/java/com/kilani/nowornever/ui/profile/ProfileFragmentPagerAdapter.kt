package com.kilani.nowornever.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kilani.nowornever.ui.profile.friends.FriendsFragment
import com.kilani.nowornever.ui.profile.infos.InfosFragment

class ProfileFragmentPagerAdapter(mgr: FragmentManager) : FragmentStatePagerAdapter(mgr, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentsMap = mutableMapOf<Int, Fragment>()

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        fragmentsMap[position] = when (position) {

            0 -> InfosFragment.newInstance()
            1 -> FriendsFragment.newInstance()
            else -> InfosFragment.newInstance()
        }
        return fragmentsMap[position]!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Mes infos"
            1 -> "Mes amis"
            else -> "Mes infos"
        }
    }

    fun getFragment(position: Int): Fragment = fragmentsMap[position]!!

}
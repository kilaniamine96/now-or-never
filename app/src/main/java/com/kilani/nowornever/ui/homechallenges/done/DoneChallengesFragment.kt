package com.kilani.nowornever.ui.homechallenges.done


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kilani.nowornever.R

/**
 * A simple [Fragment] subclass.
 */
class DoneChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = DoneChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_done_challenges, container, false)
    }


}

package com.kilani.nowornever.ui.homechallenges.received


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kilani.nowornever.R

/**
 * A simple [Fragment] subclass.
 */
class ReceivedChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = ReceivedChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_received_challenges, container, false)
    }


}

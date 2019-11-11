package com.kilani.nowornever.ui.homechallenges.current


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.kilani.nowornever.R
import com.kilani.nowornever.data.enums.ChallengeCategory
import com.kilani.nowornever.data.model.Challenge
import kotlinx.android.synthetic.main.fragment_current_challenges.*

/**
 * A simple [Fragment] subclass.
 */
class CurrentChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentChallengesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val challenge = Challenge(1,20, ChallengeCategory.FUNNY, "Va parler à cette putain de fille", "Jeremy", null, null)
        val challenge2 = Challenge(2,30, ChallengeCategory.FUNNY, "Mange du caca", "Pierre", null, null)
        currentChallengesRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrentChallengesAdapter(listOf(challenge, challenge2))
        }
    }


}

package com.kilani.nowornever.ui.homechallenges.received


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth

import com.kilani.nowornever.R
import com.kilani.nowornever.data.enums.ChallengeStatus
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.main.MainActivity
import com.kilani.nowornever.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_received_challenges.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ReceivedChallengesFragment : Fragment(), ReceivedChallengesAdapter.ReceivedChallengesListener {

    private val viewModel by sharedViewModel<MainViewModel>()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initOrUpdateRecyclerView()
    }

    private fun initOrUpdateRecyclerView() {
        viewModel.challengesList.value?.let {
            receivedChallengesRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ReceivedChallengesAdapter(it.filter { it.status == ChallengeStatus.PROPOSED }, this@ReceivedChallengesFragment)
            }
        }
    }

    private fun initObservers() {
        viewModel.challengesList.observe(
            this,
            Observer { challengesReceived -> if (challengesReceived != null) initOrUpdateRecyclerView()  }
        )
    }

    private fun updateChallenges() = viewModel.updateChallenges(FirebaseAuth.getInstance().currentUser?.displayName!!)

    override fun onDeleteChallenge(itemPosition: Int) {
        (activity as MainActivity).showConfirmationDialog {
        viewModel.challengesList.value?.removeAt(itemPosition)
        updateChallenges()
        }
    }

    override fun onAcceptChallenge(itemPosition: Int, challenge: Challenge) {
        (activity as MainActivity).showConfirmationDialog {
        challenge.status = ChallengeStatus.ACCEPTED
        updateChallenges()
        }
    }


}

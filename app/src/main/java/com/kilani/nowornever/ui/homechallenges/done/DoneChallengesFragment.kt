package com.kilani.nowornever.ui.homechallenges.done


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kilani.nowornever.R
import com.kilani.nowornever.data.enums.ChallengeStatus
import com.kilani.nowornever.ui.homechallenges.HomeChallengesViewModel
import com.kilani.nowornever.ui.homechallenges.current.CurrentChallengesAdapter
import kotlinx.android.synthetic.main.fragment_done_challenges.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class DoneChallengesFragment : Fragment(), DoneChallengesAdapter.DoneChallengesListener {


    private val viewModel by sharedViewModel<HomeChallengesViewModel>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initOrUpdateRecyclerView()
    }

    private fun initOrUpdateRecyclerView() {
        viewModel.challengesList.value?.let {
            doneChallengesRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DoneChallengesAdapter(it.filter { it.status == ChallengeStatus.DONE }, this@DoneChallengesFragment)
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
        val challengeNewList = viewModel.challengesList.value
        challengeNewList?.removeAt(itemPosition)
        viewModel.challengesList.postValue(challengeNewList)
        updateChallenges()
    }

}

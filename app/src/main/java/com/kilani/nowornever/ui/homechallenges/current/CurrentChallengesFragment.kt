package com.kilani.nowornever.ui.homechallenges.current


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.homechallenges.HomeChallengesViewModel
import kotlinx.android.synthetic.main.fragment_current_challenges.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class CurrentChallengesFragment : Fragment(), CurrentChallengesAdapter.CurrentChallengesListener {

    private val viewModel by sharedViewModel<HomeChallengesViewModel>()

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
        viewModel.getChallenges(FirebaseAuth.getInstance().currentUser!!)
        initObservers()
        initOrUpdateRecyclerView()
    }

    private fun initOrUpdateRecyclerView() {
        viewModel.challengesAccepted.value?.let {
            currentChallengesRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CurrentChallengesAdapter(it, this@CurrentChallengesFragment)
            }
        }
    }

    private fun initObservers() {
        viewModel.challengesAccepted.observe(
            this,
            Observer { challengesReceived -> if (challengesReceived != null) initOrUpdateRecyclerView()  }
        )
    }

    override fun onDeleteChallenge(itemPosition: Int) {
        val challengeNewList = viewModel.challengesAccepted.value
        challengeNewList?.removeAt(itemPosition)
        viewModel.challengesAccepted.postValue(challengeNewList)
    }

    override fun onValidateChallenge(challenge: Challenge) {
        //(activity as MainActivity).viewModel.
    }


}

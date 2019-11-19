package com.kilani.nowornever.ui.homechallenges.current.send


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.kilani.nowornever.R
import com.kilani.nowornever.data.enums.ChallengeCategory
import com.kilani.nowornever.data.enums.ChallengeStatus
import com.kilani.nowornever.data.model.Challenge
import kotlinx.android.synthetic.main.dialog_fragment_send_challenge.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class SendChallengeDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() =
            SendChallengeDialogFragment()
    }

    private val viewModel by viewModel<SendChallengeViewModel>()
    private var challenge = Challenge()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_fragment_send_challenge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initCategorySpinnerAdapter()
        initObservers()
    }

    private fun initObservers() {
        viewModel.challengeSent.observe(
            this,
            Observer { challengeSent -> if (challengeSent) dismiss() }
        )
    }

    private fun initListeners() {
        cancelBtn.setOnClickListener { dismiss() }
        sendBtn.setOnClickListener {
            fillChallenge()
            viewModel.sendChallenge(challenge.receiver!!, challenge)
        }
    }

    private fun initCategorySpinnerAdapter() {
        challengeCategorySpinner.adapter = ChallengeCategorySpinnerAdapter(
            context!!,
            android.R.layout.simple_spinner_item,
            ChallengeCategory.values(),
            getString(R.string.category)
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun fillChallenge() {
        challenge.apply {
            this.points = pointsEt.text.toString().toInt()
            this.description = descriptionEt.text.toString()
            this.sender = FirebaseAuth.getInstance().currentUser!!.displayName
            this.receiver = receiverEt.text.toString()
            this.status = ChallengeStatus.PROPOSED
            this.category = (challengeCategorySpinner.selectedItem as ChallengeCategorySpinnerAdapter.ChallengeCategoryWithHint).challengeCategory
        }
    }


}

package com.kilani.nowornever.ui.homechallenges.received

import android.view.View
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseViewHolder
import kotlinx.android.synthetic.main.layout_received_challenges_recyclerview_item.view.*

class ReceivedChallengesViewHolder(
    private val view: View,
    private val listener: ReceivedChallengesAdapter.ReceivedChallengesListener) : BaseViewHolder<Challenge>(view) {

    override fun bind(item: Challenge, itemPosition: Int) {
        view.senderTv.text = item.sender
        view.pointsTv.text = item.points.toString()
        view.descriptionTv.text = item.description
        view.deleteBtn.setOnClickListener{ listener.onDeleteChallenge(itemPosition)}
        view.validateBtn.setOnClickListener { listener.onAcceptChallenge(itemPosition, item) }
    }
}
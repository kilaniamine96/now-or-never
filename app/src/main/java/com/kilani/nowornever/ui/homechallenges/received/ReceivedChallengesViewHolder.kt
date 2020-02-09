package com.kilani.nowornever.ui.homechallenges.received

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kilani.nowornever.data.colorRes
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.data.stringRes
import com.kilani.nowornever.ui.core.BaseViewHolder
import kotlinx.android.synthetic.main.challenge_card.view.*

class ReceivedChallengesViewHolder(
    private val fragment: Fragment,
    private val view: View,
    private val listener: ReceivedChallengesAdapter.ReceivedChallengesListener) : BaseViewHolder<Challenge>(view) {

    override fun bind(item: Challenge, itemPosition: Int) {
        Glide.with(fragment)
            .load(item.senderPictureUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view.senderIv)
        view.senderTv.text = item.sender
        view.pointsTv.text = "Points du défi: " + item.points.toString()
        view.challengeTag.setBackgroundResource(item.category!!.colorRes())
        view.challengeTag.setText(item.category!!.stringRes())
        view.descriptionTv.text = item.description
        view.deleteBtn.setOnClickListener{ listener.onDeleteChallenge(itemPosition)}
        view.validateBtn.setOnClickListener { listener.onAcceptChallenge(itemPosition, item) }
    }
}
package com.kilani.nowornever.ui.homechallenges.current

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseViewHolder
import kotlinx.android.synthetic.main.challenge_card.view.*

class CurrentChallengesViewHolder(
    private val fragment: Fragment,
    private val view: View,
    private val listener: CurrentChallengesAdapter.CurrentChallengesListener) : BaseViewHolder<Challenge>(view) {

    override fun bind(item: Challenge, itemPosition: Int) {
        Glide.with(fragment)
            .load(item.senderPictureUrl)
            .placeholder(R.drawable.ic_unknown_user_symbol)
            .apply(RequestOptions.circleCropTransform())
            .into(view.senderIv)
        view.senderTv.text = item.sender
        view.pointsTv.text = "Points du défi: " + item.points.toString()
        view.descriptionTv.text = item.description
        view.deleteBtn.setOnClickListener{ listener.onDeleteChallenge(itemPosition)}
        view.validateBtn.setOnClickListener { listener.onValidateChallenge(itemPosition,item) }
    }
}
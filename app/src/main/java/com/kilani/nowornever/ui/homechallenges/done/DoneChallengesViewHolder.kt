package com.kilani.nowornever.ui.homechallenges.done

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseViewHolder
import kotlinx.android.synthetic.main.layout_received_challenges_recyclerview_item.view.*

class DoneChallengesViewHolder(
    private val fragment: Fragment,
    private val view: View,
    private val listener: DoneChallengesAdapter.DoneChallengesListener) : BaseViewHolder<Challenge>(view) {

    override fun bind(item: Challenge, itemPosition: Int) {
        Glide.with(fragment)
            .load(item.senderPictureUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view.senderIv)
        view.senderTv.text = item.sender
        view.pointsTv.text = "Points du défi: " + item.points.toString()
        view.descriptionTv.text = item.description
        view.deleteBtn.setOnClickListener{ listener.onDeleteChallenge(itemPosition)}
    }
}
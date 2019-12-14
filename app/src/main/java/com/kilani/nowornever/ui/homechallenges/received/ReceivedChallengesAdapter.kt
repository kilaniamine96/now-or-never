package com.kilani.nowornever.ui.homechallenges.received

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseAdapter
import com.kilani.nowornever.ui.core.BaseViewHolder

class ReceivedChallengesAdapter(list: List<Challenge>, private val listener: ReceivedChallengesListener) : BaseAdapter<Challenge>(list) {

    interface ReceivedChallengesListener {
        fun onDeleteChallenge(itemPosition: Int)
        fun onAcceptChallenge(itemPosition: Int, challenge: Challenge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Challenge> =
        ReceivedChallengesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_received_challenges_recyclerview_item, parent, false
            ),
            listener
        )
}
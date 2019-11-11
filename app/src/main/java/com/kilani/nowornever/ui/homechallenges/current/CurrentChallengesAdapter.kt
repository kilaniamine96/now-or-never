package com.kilani.nowornever.ui.homechallenges.current

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseAdapter
import com.kilani.nowornever.ui.core.BaseViewHolder

class CurrentChallengesAdapter(list: List<Challenge>) : BaseAdapter<Challenge>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Challenge> =
        CurrentChallengesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_challenges_recyclerview_item, parent, false
            )
        )
}
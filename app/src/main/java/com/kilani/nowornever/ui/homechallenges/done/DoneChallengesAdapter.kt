package com.kilani.nowornever.ui.homechallenges.done

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.ui.core.BaseAdapter
import com.kilani.nowornever.ui.core.BaseViewHolder

class DoneChallengesAdapter(private val fragment: Fragment, list: List<Challenge>, private val listener: DoneChallengesListener) : BaseAdapter<Challenge>(list) {

    interface DoneChallengesListener {
        fun onDeleteChallenge(itemPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Challenge> =
        DoneChallengesViewHolder(
            fragment,
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_done_challenges_recyclerview_item, parent, false
            ),
            listener
        )
}
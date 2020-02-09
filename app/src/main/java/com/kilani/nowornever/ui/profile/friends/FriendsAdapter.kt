package com.kilani.nowornever.ui.profile.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.ui.core.BaseAdapter
import com.kilani.nowornever.ui.core.BaseViewHolder

class FriendsAdapter (private val fragment: Fragment, list: List<User>) : BaseAdapter<User>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> =
        FriendsViewHolder(
            fragment,
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_friend_recyclerview_item, parent, false
            )
        )
}
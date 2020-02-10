package com.kilani.nowornever.ui.profile.friends

import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.data.stringRes
import com.kilani.nowornever.ui.core.BaseViewHolder
import kotlinx.android.synthetic.main.layout_friend_recyclerview_item.view.*

class FriendsViewHolder(
    private val fragment: Fragment,
    private val view: View) : BaseViewHolder<User>(view) {

    override fun bind(item: User, itemPosition: Int) {
        Glide.with(fragment)
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_unknown_user_symbol)
            .apply(RequestOptions.circleCropTransform())
            .into(view.friendIv)
        view.friendNameTv.text = item.name
        view.levelTv.setText(item.level!!.stringRes())
    }
}
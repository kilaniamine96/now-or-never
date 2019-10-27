package com.kilani.nowornever.ui.core

import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * Created by Frederic on 13/10/2017.
 */

abstract class BaseViewHolder<T: Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)
}

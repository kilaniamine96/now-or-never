package com.kilani.nowornever.ui.core

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Frederic on 13/10/2017.
 */

abstract class BaseAdapter<T : Any>(objectList: List<T>) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var mObjectList: List<T>? = ArrayList()

    init {
        mObjectList = objectList
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val objectItem = mObjectList!![position]
        holder.bind(objectItem, position)
    }

    fun setObjectList(objectList: List<T>) {
        mObjectList = objectList
    }

    fun getItem(position: Int): T {
        return mObjectList!![position]
    }

    override fun getItemCount(): Int {
        return if (mObjectList != null) mObjectList!!.size else 0
    }
}
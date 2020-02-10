package com.kilani.nowornever.ui.profile.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.Fragment
import com.kilani.nowornever.R
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.ui.core.BaseAdapter
import com.kilani.nowornever.ui.core.BaseViewHolder
import java.util.*
import kotlin.collections.ArrayList

class FriendsAdapter (private val fragment: Fragment, private val searchableList: MutableList<User>) : BaseAdapter<User>(searchableList), Filterable {

    private val originalList = ArrayList(searchableList)

    private var onNothingFound: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<User> =
        FriendsViewHolder(
            fragment,
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_friend_recyclerview_item, parent, false
            )
        )

    fun search(s: String? , onNothingFound: (() -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter = object : Filter() {
            private val filterResults = FilterResults()

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                searchableList.clear()
                if (constraint.isNullOrBlank()) searchableList.addAll(originalList)
                else {
                    val searchResults = originalList.filter { it.name!!.toLowerCase(Locale.getDefault()).contains(constraint) }
                    searchableList.addAll(searchResults)
                }
                return filterResults.also { it.values = searchableList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (searchableList.isNullOrEmpty()) onNothingFound?.invoke()
                notifyDataSetChanged()
            }
        }
}
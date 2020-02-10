package com.kilani.nowornever.ui.profile.friends


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth

import com.kilani.nowornever.R
import com.kilani.nowornever.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class FriendsFragment : Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        fun newInstance() = FriendsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOrUpdateRecyclerView()
        initListeners()
    }

    private fun initOrUpdateRecyclerView() {
        viewModel.usersList.value?.let{
            friendsRv.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = FriendsAdapter(this@FriendsFragment, it)
            }
        }
    }

    private fun initListeners() {
        friendsSv.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText)
        return true
    }

    private fun search(s: String?) {
        (friendsRv.adapter as FriendsAdapter).search(s) {
            //TODO: onNothingFound
        }
    }
}

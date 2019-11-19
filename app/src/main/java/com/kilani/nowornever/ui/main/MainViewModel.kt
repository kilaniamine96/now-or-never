package com.kilani.nowornever.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.kilani.nowornever.data.repository.UserRepository
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.utils.Coroutines

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    val challengesList = MutableLiveData<List<Challenge>>().apply { value = null }

    fun getUser(user: FirebaseUser) {
        Coroutines.io {
            userRepository.getUser(user, onSuccess = {
                user ->  challengesList.postValue(user.challenges)
            })
        }
    }
}
package com.kilani.nowornever.ui.homechallenges

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.kilani.nowornever.data.enums.ChallengeStatus
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.data.repository.ChallengeRepository
import com.kilani.nowornever.data.repository.UserRepository
import com.kilani.nowornever.utils.Coroutines


class HomeChallengesViewModel(
    private val challengeRepository: ChallengeRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val challengesList = MutableLiveData<MutableList<Challenge>>().apply { value = null }

    fun getChallenges(user: FirebaseUser) {
        Coroutines.io {
            userRepository.getUser(user, onSuccess = { user ->
                challengesList.postValue(user.challenges)
            })
        }
    }

    fun updateChallenges(username: String) {
        Coroutines.io {
            challengeRepository.updateChallenge(username,challengesList.value!!)
        }
    }
}


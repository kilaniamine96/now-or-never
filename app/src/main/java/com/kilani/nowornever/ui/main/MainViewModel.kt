package com.kilani.nowornever.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.kilani.nowornever.data.repository.UserRepository
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.data.model.User
import com.kilani.nowornever.data.repository.ChallengeRepository
import com.kilani.nowornever.utils.Coroutines

class MainViewModel(private val userRepository: UserRepository, private val challengeRepository: ChallengeRepository) : ViewModel() {

    val challengesList = MutableLiveData<MutableList<Challenge>>().apply { value = null }
    val currentUser = MutableLiveData<User>().apply { value = null }
    val usersList = MutableLiveData<MutableList<User>>().apply { value = null }

    fun getUser(user: FirebaseUser) {
        Coroutines.io {
            userRepository.getUser(user, onSuccess = {
                remoteUser ->
                challengesList.postValue(remoteUser.challenges)
                currentUser.postValue(remoteUser)
            })
        }
    }

    fun getAllUsers() {
        Coroutines.io {
            userRepository.getAllUsers( onSuccess =  {
                userList ->  usersList.postValue(userList)
            })
        }
    }

    fun updateChallenges(username: String) {
        Coroutines.io {
            challengeRepository.updateChallenge(username,challengesList.value!!)
        }
    }

    fun listenForUserUpdates(user: FirebaseUser) {
        Coroutines.io {
            userRepository.listenForUserUpdates(user, onSuccess = {
                userUpdated ->
                challengesList.postValue(userUpdated.challenges)
                currentUser.postValue(userUpdated)
            })
        }
    }

    fun stopListeningForUpdates() = userRepository.stopListeningForUpdates()


    fun updateUserScore(user: FirebaseUser, score: Int) {
        Coroutines.io {
            val updatedUser = currentUser.value as User
            updatedUser.score = updatedUser.score?.plus(score)
            userRepository.updateUserScore(updatedUser.score!!, user, onSuccess = { currentUser.postValue(updatedUser)}  )
        }
    }
}
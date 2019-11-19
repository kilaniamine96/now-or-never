package com.kilani.nowornever.ui.homechallenges.current.send

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.data.repository.ChallengeRepository
import com.kilani.nowornever.utils.Coroutines

class SendChallengeViewModel(
    private val challengeRepository: ChallengeRepository
) : ViewModel() {

    val challengeSent = MutableLiveData<Boolean>().apply { value = false }

    fun sendChallenge(username: String, challenge: Challenge) {
        Coroutines.io {
            challengeRepository.sendChallengeToUser(username,challenge, onSuccess = { challengeSent.postValue(true)} )
        }
    }
}
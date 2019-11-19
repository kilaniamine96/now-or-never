package com.kilani.nowornever.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kilani.nowornever.data.enums.ChallengeStatus
import com.kilani.nowornever.data.model.Challenge

class ChallengeRepository {

    private val dbCollection = FirebaseFirestore.getInstance().collection("users")

    fun sendChallengeToUser( username : String, challenge: Challenge, onSuccess: () -> Unit) {
        challenge.status = ChallengeStatus.PROPOSED
        dbCollection.document(username)
            .update("challenges", FieldValue.arrayUnion(challenge))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> Log.w("ChallengeRepository", "Error sending challenge", e) }
    }

    fun updateChallenge(username: String, challengeList: MutableList<Challenge>) {
        dbCollection.document(username)
            .update("challenges", challengeList)
            .addOnFailureListener { e -> Log.w("ChallengeRepository", "Error updating challenge", e) }
    }
}
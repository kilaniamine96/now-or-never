package com.kilani.nowornever.data

import com.google.firebase.auth.FirebaseUser
import com.kilani.nowornever.data.model.Challenge
import com.kilani.nowornever.data.model.User

//User

fun FirebaseUser.toModel(score: Int, challenges: MutableList<Challenge>) = User(
    id = uid,
    name = displayName,
    imageUrl = photoUrl.toString(),
    score = score,
    challenges = challenges
)
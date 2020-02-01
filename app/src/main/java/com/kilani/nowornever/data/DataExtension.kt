package com.kilani.nowornever.data

import com.google.firebase.auth.FirebaseUser
import com.kilani.nowornever.data.database.entities.UserEntity
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

fun User.toEntity() = UserEntity(
    uid = id,
    name = name,
    imageUrl = imageUrl,
    score = score
)
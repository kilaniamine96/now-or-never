package com.kilani.nowornever.data.model

import com.kilani.nowornever.data.enums.Level

data class User(
    var id: String = "",
    var name: String? = "",
    var imageUrl: String? = "",
    var challenges: MutableList<Challenge>? = null,
    var score: Int? = 0,
    var level: Level? = Level.CANARI
)

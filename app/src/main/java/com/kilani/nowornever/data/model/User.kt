package com.kilani.nowornever.data.model

data class User(
    var id: String = "",
    var name: String? = "",
    var imageUrl: String? = "",
    var challenges: MutableList<Challenge>? = null,
    var score: Int? = 0
)

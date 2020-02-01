package com.kilani.nowornever.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kilani.nowornever.data.enums.Level
import com.kilani.nowornever.data.model.Challenge

@Entity
class FriendEntity(@PrimaryKey (autoGenerate = false)
                    var uid: Long,
                   var name: String?,
                   var imageUrl: String?,
                   var challenges: MutableList<Challenge>?,
                   var score: Int?,
                   var level: Level?)
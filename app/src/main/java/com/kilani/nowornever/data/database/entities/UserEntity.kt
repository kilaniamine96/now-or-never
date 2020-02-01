package com.kilani.nowornever.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity(@PrimaryKey (autoGenerate = false)
                    var uid: String,
                   var name: String?,
                   var imageUrl: String?,
                   var score: Int?)
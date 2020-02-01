package com.kilani.nowornever.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kilani.nowornever.data.database.entities.FriendEntity

@Dao
interface FriendsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(entities: List<FriendEntity>)

    @Query("SELECT * FROM FriendEntity")
    fun getAll(): List<FriendEntity>
}
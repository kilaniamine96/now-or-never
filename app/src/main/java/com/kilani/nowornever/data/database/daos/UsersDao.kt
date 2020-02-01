package com.kilani.nowornever.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kilani.nowornever.data.database.entities.UserEntity
import com.kilani.nowornever.data.model.User

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(entities: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>
}
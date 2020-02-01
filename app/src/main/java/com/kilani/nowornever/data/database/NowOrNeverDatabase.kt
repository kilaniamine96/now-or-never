package com.kilani.nowornever.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kilani.nowornever.data.database.daos.UsersDao
import com.kilani.nowornever.data.database.entities.UserEntity

@Database(
    version = 2, entities =
    [UserEntity::class]
)
abstract class NowOrNeverDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
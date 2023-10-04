package com.example.testapp.Repository.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface Dao {
    @Insert(entity = User::class)
    fun insert(user: User)

    @Query("DELETE FROM user")
    fun delete()

    @Query("SELECT * FROM user")
    fun select ():List<User>
}

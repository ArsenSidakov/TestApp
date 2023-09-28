package com.example.testapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert(entity = User::class)
    fun insert(user: User)

    @Query("DELETE FROM user")
    fun delete()

    @Query("SELECT * FROM user")
    fun select ():List<User>
}

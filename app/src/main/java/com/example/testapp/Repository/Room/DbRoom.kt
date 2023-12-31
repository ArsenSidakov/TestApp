package com.example.testapp.Repository.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
    abstract class Bdroom:RoomDatabase() {
        abstract fun getdao(): Dao

        companion object {
            fun getdb(context: Context): Bdroom {
                return Room.databaseBuilder(
                    context,
                    Bdroom::class.java,
                    "room.db"
                ).build()
            }
        }
    }
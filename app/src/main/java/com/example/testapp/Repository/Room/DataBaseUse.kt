package com.example.testapp.Repository

import android.content.Context
import android.util.Log
import android.widget.EditText
import com.example.testapp.Repository.Room.Bdroom
import com.example.testapp.Repository.Room.Dao
import com.example.testapp.Repository.Room.User
import com.example.testapp.ViewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class DataBaseUse(context: Context) {

    val room = Bdroom.getdb(context)

   fun insert(user: User){
       CoroutineScope(Dispatchers.IO).launch{
        room.getdao().insert(user)
       }
    }

    fun delete (){
        room.getdao().delete()
    }

//lateinit var login: String
//    fun select(){
//        CoroutineScope(Dispatchers.IO).launch{
//             room.getdao().select().forEach {
//                mainViewModel.liveDataLoginEd.value =it.login.toString()
//            }
//          }
//        Log.d("Mylog","saa")
//    }
}
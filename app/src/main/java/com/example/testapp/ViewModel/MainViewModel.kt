package com.example.testapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testapp.Repository.Room.Bdroom
import kotlinx.coroutines.CoroutineScope
import kotlin.concurrent.thread

class MainViewModel(app:Application) : AndroidViewModel(app) {

    var liveDataLoginEd = MutableLiveData<String>()
    var liveDataLoginView = MutableLiveData<String>()
    var liveDataPassEd = MutableLiveData<String>()
    var liveDataPassView = MutableLiveData<String>()
    var liveDataCheckingUser = MutableLiveData<String?>()
    var string: String? = null
    var checkResultLogin: Int? = 0
    var chekResultPassword: Int? = null
    val room = Bdroom.getdb(app)
    init {
    searchForUser()

    }

//    fun chek (){
//        if (checkLogin() )
//    }
    fun searchForUser(){
        Thread {
            room.getdao().select().forEach {
                liveDataCheckingUser.value = it.login
            }
        }.start()
    }


    fun checkLogin() {
        liveDataLoginEd.value?.let {
            if (it.length <= 2) {
                liveDataLoginView.value = "Логин должен содержать болeе \n 3х символов"
            } else {
                liveDataLoginView.value = ""
                if (it.contains(".") || it.contains(" ")) {
                    liveDataLoginView.value = "Логин не должен содержать . или ' ' "
                } else {
                    checkResultLogin = 1
                }
            }
        }
    }


    fun checkPassword() {
        liveDataPassEd.value?.let {
            if (liveDataPassEd.value?.length!! <= 5) {
                liveDataPassView.value = "Пароль должен содержать \n более 6 символов"
            } else {
                liveDataPassView.value = ""
                for (i in 1 until liveDataPassEd.value?.length!!) {
                    if (liveDataPassEd.value!![i] == liveDataPassEd.value!![i - 1]) {
                        liveDataPassView.value = "Пароль не должен содержать подряд \n одинаковых символов"
                        break
                    } else {

                        string = liveDataPassEd.value?.filter { it.isDigit() }
                        if ((string?.length!! <= 2) || (liveDataPassEd.value!!.length - string!!.length) < 3) {
                            liveDataPassView.value =
                                "Пароль должен содержать \n не менее 3х символов и 3х чисел"

                        } else chekResultPassword = 1
                    }
                }
            }
        }

    }


}
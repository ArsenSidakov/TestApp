package com.example.testapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.testapp.Room.Bdroom
import com.example.testapp.Room.User

class MainViewModel(app:Application) : AndroidViewModel(app) {

    var livedataEd = MutableLiveData<String>()
    var livedataView = MutableLiveData<String>()
    var livedataEd1 = MutableLiveData<String>()
    var livedataView1 = MutableLiveData<String>()
    var result = MutableLiveData<Int>()
    var string: String? = null
    var log: Int? = null
    var pass: Int? = null
    val room = Bdroom.getdb(app)
    init {

    }




    fun login() {
        log = 0
        if (livedataEd.value?.length!! <= 2) {
            livedataView.value = "Логин должен содержать болeе \n 3х символов"

        } else {
            livedataView.value = ""
            if (livedataEd.value!!.contains(".") || livedataEd.value!!.contains(" ")) {
                livedataView.value = "Логин не должен содержать . или ' ' "
            } else log = 1
        }
    }

    fun password() {
        pass = 0
        if (livedataEd1.value?.length!! <= 5) {
            livedataView1.value = "Пароль должен содержать \n более 6 символов"
        } else {
            livedataView1.value = ""
            for (i in 1 until livedataEd1.value?.length!!) {
                if (livedataEd1.value!![i] == livedataEd1.value!![i - 1]) {
                    livedataView1.value = "Пароль не должен содержать подряд \n одинаковых символов"
                    break
                } else {

                    string = livedataEd1.value?.filter { it.isDigit() }
                    if ((string?.length!! <= 2) || (livedataEd1.value!!.length - string!!.length) < 3) {
                        livedataView1.value =
                            "Пароль должен содержать \n не менее 3х символов и 3х чисел"

                    } else pass = 1
                }
            }
        }
    }


}
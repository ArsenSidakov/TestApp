package com.example.testapp.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.widget.Toast
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.Room.Bdroom
import com.example.testapp.Room.User
import com.example.testapp.ViewModel.MainViewModel
import com.example.testapp.databinding.ActivitySecondBinding
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var mainViewModel: MainViewModel
    private var timer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var name = intent.getStringExtra("key2")
        binding.nameView.text = "Hello $name"
        timerCounDouwn(10000)
        binding.backButton.setOnClickListener {
            finishAffinity()
        }
        binding.deleteButton.setOnClickListener{
            val i = Intent()
            i.putExtra("1", 1)
            setResult(RESULT_OK,i)
            finish()
        }
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })
    }
    private fun timerCounDouwn(time: Long) {
        timer?.cancel()

        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
               val i = Intent()
                i.putExtra("1", 1)
                setResult(RESULT_OK,i)
                finish()
            }
        }.start()
    }
}
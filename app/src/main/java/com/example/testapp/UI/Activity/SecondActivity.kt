package com.example.testapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.OnBackPressedCallback
import com.example.testapp.ViewModel.MainViewModel
import com.example.testapp.databinding.ActivitySecondBinding

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
        timerCounDouwn(360000)
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
            override fun handleOnBackPressed() {}
        })
    }
    private fun timerCounDouwn(time: Long) {
        timer?.cancel()

        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
               val resultForMainActivity = Intent()
                resultForMainActivity.putExtra("1", 1)
                setResult(RESULT_OK,resultForMainActivity)
                finish()
            }
        }.start()
    }
}
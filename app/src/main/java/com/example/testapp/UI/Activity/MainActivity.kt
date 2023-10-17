package com.example.testapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.Repository.Room.User
import com.example.testapp.ViewModel.MainViewModel
import com.example.testapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var user: User
    private var launcher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                CoroutineScope(Dispatchers.IO).launch {
                    mainViewModel.deleteDB()
                }
                Toast.makeText(this, "Пользователь удален", Toast.LENGTH_LONG).show()
            }
        }
//Слушатель нажатий ELogin
        mainViewModel.liveDataLoginView.observe(this) {
            binding.loginView.text = mainViewModel.liveDataLoginView.value.toString()
        }
        binding.loginEd.addTextChangedListener {
            mainViewModel.liveDataLoginEd.value = binding.loginEd.text.toString()
            mainViewModel. checkLogin()
        }
//Слушатель нажатий Password
        mainViewModel.liveDataPassView.observe(this) {
            binding.passView.text = mainViewModel.liveDataPassView.value.toString()
        }
        binding.passwordEd.addTextChangedListener {
            mainViewModel.liveDataPassEd.value = binding.passwordEd.text.toString()
            mainViewModel.checkPassword()
        }

//Слушатель нажатий OK
        binding.buttonOK.setOnClickListener {
            if (mainViewModel.checkAll()) {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("key2", binding.loginEd.text.toString())
                }
                launcher?.launch(intent)
                user = User(
                    null,
                    binding.loginEd.text.toString(),
                    binding.passwordEd.text.toString()
                )
                mainViewModel.insertDB(user)

            } else {
                Toast.makeText(this, "Пароль или логин введены не правильно", Toast.LENGTH_LONG)
                    .show()

            }
        }



                 mainViewModel.selectDB()


//        Log.d("MyLog","$login")
//                val intent = Intent(this, SecondActivity::class.java).apply {
//                    putExtra("key2"," $login")
//                }
//                launcher?.launch(intent)

        }
    }



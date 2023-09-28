package com.example.testapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.testapp.Room.User
import com.example.testapp.ViewModel.MainViewModel
import com.example.testapp.databinding.ActivityMainBinding

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
                Thread {
                    mainViewModel.room.getdao().delete()
                }.start()
                Toast.makeText(this, "Пользователь удален", Toast.LENGTH_LONG).show()
            }
        }
//Слушатель нажатий EditText(Проверка условий ввода Login)
        binding.loginEd.addTextChangedListener {
            mainViewModel.livedataView.observe(this) {
                binding.loginView.text = mainViewModel.livedataView.value.toString()
            }
            mainViewModel.livedataEd.value = binding.loginEd.text.toString()
            mainViewModel.login()
        }
//Слушатель нажатий EditText(Проверка условий ввода Password)
        binding.passwordEd.addTextChangedListener {
            mainViewModel.livedataView1.observe(this) {
                binding.passView.text = mainViewModel.livedataView1.value.toString()
            }
            mainViewModel.livedataEd1.value = binding.passwordEd.text.toString()
            mainViewModel.password()
        }
//Создание пользователя по нажатию на кнопку Ок

        binding.button.setOnClickListener {
            if (mainViewModel.log == 1 && mainViewModel.pass == 1) {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("key2", binding.loginEd.text.toString())
                }
                launcher?.launch(intent)
                user = User(
                    null,
                    binding.loginEd.text.toString(),
                    binding.passwordEd.text.toString()
                )
                Thread {
                    mainViewModel.room.getdao().insert(user)
                }.start()

            } else {
                Toast.makeText(this, "Пароль или логин введены не правильно", Toast.LENGTH_LONG)
                    .show()

            }
        }

        Thread{
        mainViewModel.room.getdao().select().forEach {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("key2"," ${it.login}")
                }
                launcher?.launch(intent)
            }
        }.start()
    }
}


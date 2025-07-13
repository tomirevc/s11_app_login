package com.example.s11_app_login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.s11_app_login.databinding.ActivityMainBinding
import com.example.s11_app_login.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM.userLiveData.observe(this) { user ->
            binding.tvBienvenido.text = "Bienvenido ${user?.nombre ?: ""}"
        }

        mainVM.loadUserData()

        binding.btnLogout.setOnClickListener {
            mainVM.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

package com.example.s11_app_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.s11_app_login.repository.AuthRepository
import com.example.s11_app_login.repository.UserRepository
import com.example.s11_app_login.model.User

class MainViewModel : ViewModel() {

    private val authRepo = AuthRepository()
    private val userRepo = UserRepository()

    val userLiveData = userRepo.userLiveData

    fun loadUserData() {
        val user = authRepo.getCurrentUser()
        user?.let {
            userRepo.loadUser(it.uid)
        }
    }

    fun logout() {
        authRepo.logout()
    }
}

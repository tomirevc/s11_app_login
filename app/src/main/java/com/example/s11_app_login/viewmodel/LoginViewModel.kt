package com.example.s11_app_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s11_app_login.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val authRepo = AuthRepository()

    private val _loginResult = MutableLiveData<Pair<Boolean, String?>>()
    val loginResult: LiveData<Pair<Boolean, String?>> get() = _loginResult

    fun login(email: String, password: String) {
        authRepo.login(email, password) { success, message ->
            _loginResult.postValue(Pair(success, message))
        }
    }

}

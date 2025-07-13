package com.example.s11_app_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s11_app_login.repository.AuthRepository

class RegisterViewModel : ViewModel() {

    private val authRepo = AuthRepository()

    private val _registerResult = MutableLiveData<Pair<Boolean, String?>>()
    val registerResult: LiveData<Pair<Boolean, String?>> get() = _registerResult

    // Registro clásico con correo y contraseña
    fun register(nombre: String, email: String, password: String) {
        authRepo.register(nombre, email, password) { success, message ->
            _registerResult.postValue(Pair(success, message))
        }
    }

}

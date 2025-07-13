package com.example.s11_app_login.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.s11_app_login.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> get() = _userLiveData

    fun loadUser(uid: String) {
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                val user = doc.toObject(User::class.java)
                _userLiveData.value = user!!
            }
    }
}

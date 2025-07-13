package com.example.s11_app_login.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.example.s11_app_login.model.User

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> get() = _firebaseUser

    fun login(email: String, password: String, result: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _firebaseUser.value = auth.currentUser
                    result(true, null)
                } else {
                    result(false, task.exception?.message)
                }
            }
    }

    fun register(nombre: String, email: String, password: String, result: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = User(auth.currentUser!!.uid, nombre, email)
                    firestore.collection("users").document(user.uid).set(user)
                        .addOnSuccessListener {
                            _firebaseUser.value = auth.currentUser
                            result(true, null)
                        }
                        .addOnFailureListener { e ->
                            result(false, e.message)
                        }
                } else {
                    result(false, task.exception?.message)
                }
            }
    }

    fun logout() {
        auth.signOut()
        _firebaseUser.value = null
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}

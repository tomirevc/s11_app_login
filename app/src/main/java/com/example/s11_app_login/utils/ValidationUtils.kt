package com.example.s11_app_login.utils

object ValidationUtils {
    fun isNombreValid(nombre: String): Boolean = nombre.trim().isNotEmpty()

    fun isEmailValid(email: String): Boolean =
        email.trim().isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean =
        password.trim().length >= 6

    fun validateRegisterFields(nombre: String, email: String, password: String): Pair<Boolean, String?> {
        return when {
            nombre.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() ->
                Pair(false, "Por favor, completa todos los campos.")
            !isEmailValid(email) ->
                Pair(false, "El correo electrónico no es válido.")
            !isPasswordValid(password) ->
                Pair(false, "La contraseña debe tener al menos 6 caracteres.")
            else -> Pair(true, null)
        }
    }
}

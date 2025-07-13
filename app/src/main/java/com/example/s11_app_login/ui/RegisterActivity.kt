package com.example.s11_app_login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.s11_app_login.R
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.s11_app_login.databinding.ActivityRegisterBinding
import com.example.s11_app_login.viewmodel.RegisterViewModel
import com.example.s11_app_login.utils.ValidationUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerVM: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LIMPIA MENSAJE cuando se edita algún campo
        val clearMsgWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.tvRegisterMessage.visibility = android.view.View.GONE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        binding.etUsuario.addTextChangedListener(clearMsgWatcher)
        binding.etEmail.addTextChangedListener(clearMsgWatcher)
        binding.etPassword.addTextChangedListener(clearMsgWatcher)

        binding.btnRegistrarse.setOnClickListener {
            val nombre = binding.etUsuario.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            val (valido, mensaje) = ValidationUtils.validateRegisterFields(nombre, email, pass)
            if (!valido) {
                if (nombre.isEmpty()) binding.tilUsuario.error = "Ingrese un nombre de usuario" else binding.tilUsuario.error = null
                if (!ValidationUtils.isEmailValid(email)) binding.tilEmail.error = "Ingrese un email válido" else binding.tilEmail.error = null
                if (!ValidationUtils.isPasswordValid(pass)) binding.tilPassword.error = "La contraseña debe tener al menos 6 caracteres" else binding.tilPassword.error = null

                binding.tvRegisterMessage.text = mensaje
                binding.tvRegisterMessage.setTextColor(getColor(com.example.s11_app_login.R.color.error)) // O usa otro color si no tienes error
                binding.tvRegisterMessage.visibility = android.view.View.VISIBLE
            } else {
                binding.tilUsuario.error = null
                binding.tilEmail.error = null
                binding.tilPassword.error = null
                binding.tvRegisterMessage.visibility = android.view.View.GONE
                registerVM.register(nombre, email, pass)
            }
        }

        registerVM.registerResult.observe(this) { (success, msg) ->
            if (success) {
                // Limpiar campos y errores
                binding.etUsuario.setText("")
                binding.etEmail.setText("")
                binding.etPassword.setText("")
                binding.tilUsuario.error = null
                binding.tilEmail.error = null
                binding.tilPassword.error = null

                // Mostrar mensaje de éxito
                binding.tvRegisterMessage.text = "Usuario registrado correctamente"
                binding.tvRegisterMessage.setTextColor(getColor(R.color.success))
                binding.tvRegisterMessage.visibility = View.VISIBLE

                // Retrasar la navegación para que el usuario vea el mensaje
                binding.tvRegisterMessage.postDelayed({
                    binding.tvRegisterMessage.visibility = View.GONE
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 1500)
            } else {
                binding.tvRegisterMessage.text = msg ?: "Error"
                binding.tvRegisterMessage.setTextColor(getColor(R.color.error))
                binding.tvRegisterMessage.visibility = View.VISIBLE
            }
        }


        binding.btnIniciarSesion.setOnClickListener {
            finish()
        }
    }
}

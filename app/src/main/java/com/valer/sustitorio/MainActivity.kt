package com.valer.sustitorio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.valer.sustitorio.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonContinuar.setOnClickListener { validate()}
    }

    private fun validateEmail() : Boolean {
        val email = binding.editText1.layout?.text.toString()
        return if (email.isEmpty()){
            binding.editText1.error = "Este campo no puede estar vacio"
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.editText1.error = "Por favor, escriba un correo valido"
            false
        }else {
            binding.editText1.error = null
            true
        }
    }

    private fun validatePassword() : Boolean {
        val password = binding.editText2.layout?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=\\S+$)" + //no se permiten espacios
                    ".{6,}" +     //minimo 6 caracteres
                    "$"
        )
        return if (password.isEmpty()) {
            binding.editText2.error = "Este campo no puede estar vacio"
            false
        }else if (!passwordRegex.matcher(password).matches()) {
            binding.editText2.error = "La contraseña no cumple con los 6 caracteres"
            false
        }else{
            binding.editText2.error = null
            true
        }
    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validatePassword())

        if (false in result) {
            return
        }
        Toast.makeText(this, "Cuenta autentica", Toast.LENGTH_SHORT).show()
    }
}
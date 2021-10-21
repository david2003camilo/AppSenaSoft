package com.sena.quehaypahacer.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sena.quehaypahacer.MainActivity
import com.sena.quehaypahacer.databinding.ActivityLoginBinding
import com.sena.quehaypahacer.ui.register.Register

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvRegistrarse.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        binding.btnIngresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {  }
            startActivity(intent)
        }
    }
}
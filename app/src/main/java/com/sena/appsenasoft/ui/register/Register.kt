package com.sena.quehaypahacer.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.sena.quehaypahacer.databinding.ActivityRegisterBinding
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.bd.entitys.Usuarios

class Register : AppCompatActivity() {
    lateinit var baseDatos: AppBaseDatos
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
            baseDatos= Room.databaseBuilder(this, AppBaseDatos::class.java,  AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
            var respuesta = ""
            Toast.makeText(this, "Se selecciono $respuesta", Toast.LENGTH_SHORT).show()
            binding.rbMer.setOnClickListener {
                respuesta="merodeador"
                Toast.makeText(this, "Se selecciono $respuesta", Toast.LENGTH_SHORT).show()
            }
            binding.rbPub.setOnClickListener {
                respuesta="publicador"
                Toast.makeText(this, "Selecciono $respuesta", Toast.LENGTH_SHORT).show()
            }
            binding.btnRegistrarse.setOnClickListener {
                val usuario = Usuarios(
                    0,
                    binding.etEmailRe.text.toString(),
                    binding.etPasswordRe.text.toString(),
                    binding.etPassworfReTwo.text.toString(),
                    respuesta,
                    binding.etUsername.text.toString()
                )
                baseDatos.usuarioDao.insertarUsuario(usuario)
                Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_SHORT).show()
                binding.etEmailRe.text.clear()
                binding.etPasswordRe.text.clear()
                binding.etPassworfReTwo.text.clear()
                binding.etUsername.text.clear()
            }
    }
}


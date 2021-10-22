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
        binding.btnRegistrarse.setOnClickListener {
            if(binding.etEmailRe.text.isEmpty() || binding.etPasswordRe.text.isEmpty() || binding.etPassworfReTwo.text.isEmpty() || binding.etUsername.text.isEmpty()){
                Toast.makeText(this, "Algun campo esta vacio, porfavor llenarlo", Toast.LENGTH_SHORT).show()
            }else{
                val usuario = Usuarios(
                    0,
                    binding.etEmailRe.text.toString(),
                    binding.etPasswordRe.text.toString(),
                    binding.etPassworfReTwo.text.toString(),
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

}


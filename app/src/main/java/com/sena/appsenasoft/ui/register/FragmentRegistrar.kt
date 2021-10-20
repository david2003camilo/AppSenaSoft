package com.sena.appsenasoft.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.room.Room
import com.sena.appsenasoft.R
import com.sena.appsenasoft.bd.AppBaseDatos
import com.sena.appsenasoft.bd.entitys.Usuarios
import com.sena.appsenasoft.databinding.FragmentRegistrarBinding


class FragmentRegistrar : Fragment() {

    lateinit var baseDatos:AppBaseDatos
    private var _binding: FragmentRegistrarBinding? = null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRegistrarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos= Room.databaseBuilder(view.context, AppBaseDatos::class.java,  AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        var respuesta = ""
        Toast.makeText(view.context, "Se selecciono $respuesta", Toast.LENGTH_SHORT).show()
        binding.rbMer.setOnClickListener {
            respuesta="merodeador"
            Toast.makeText(view.context, "Se selecciono $respuesta", Toast.LENGTH_SHORT).show()
        }
        binding.rbPub.setOnClickListener {
            respuesta="publicador"
            Toast.makeText(view.context, "Selecciono $respuesta", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(view.context, "Se guardo correctamente", Toast.LENGTH_SHORT).show()
            binding.etEmailRe.text.clear()
            binding.etPasswordRe.text.clear()
            binding.etPassworfReTwo.text.clear()
            binding.etUsername.text.clear()
        }
    }

}
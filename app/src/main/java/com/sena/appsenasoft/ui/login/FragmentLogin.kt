package com.sena.appsenasoft.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.sena.appsenasoft.R
import com.sena.appsenasoft.bd.AppBaseDatos
import com.sena.appsenasoft.databinding.FragmentLoginBinding


class FragmentLogin : Fragment() {

    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var baseDatos: AppBaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos = Room.databaseBuilder(view.context, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        binding.tvRegistrarse.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_login_to_fragmentRegistrar)
        }
    }

}
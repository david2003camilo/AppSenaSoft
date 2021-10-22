package com.sena.quehaypahacer.ui

import android.app.Activity
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sena.quehaypahacer.adaptadores.AdaptadorServicios
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.databinding.FragmentListaServiciosBinding


class FragmentListaServicios : Fragment() {

    private var _binding: FragmentListaServiciosBinding? = null
    private val binding get() = _binding!!
    lateinit var baseDatos: AppBaseDatos
    private var adaptador: AdaptadorServicios = AdaptadorServicios()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentListaServiciosBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos = Room.databaseBuilder(view.context, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        binding.rvListaServicios.setHasFixedSize(true)
        binding.rvListaServicios.layoutManager = LinearLayoutManager(view.context)
        adaptador.AdaptadorServicios(baseDatos.serviciosDao.buscarServicios(), view.context)
        binding.rvListaServicios.adapter = adaptador
    }

}
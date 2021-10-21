package com.sena.quehaypahacer.ui.historial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sena.quehaypahacer.R
import com.sena.quehaypahacer.adaptadores.AdapterHistorial
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.databinding.FragmentHistorialBinding

class FragmentHistorial : Fragment() {

    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!
    lateinit var baseDatos: AppBaseDatos
    private var adaptador: AdapterHistorial = AdapterHistorial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHistorialBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos= Room.databaseBuilder(view.context, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        binding.rvHistorial.setHasFixedSize(true)
        binding.rvHistorial.layoutManager = LinearLayoutManager(context)
        adaptador.AdapterHistorial(baseDatos.historialDao.buscarHistorial(), view.context )
        binding.rvHistorial.adapter=adaptador
    }

}
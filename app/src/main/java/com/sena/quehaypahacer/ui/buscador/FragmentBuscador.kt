package com.sena.quehaypahacer.ui.buscador

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sena.quehaypahacer.databinding.FragmentBuscadorBinding
import com.sena.quehaypahacer.adaptadores.AdaptadorPublicacion
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.ui.viewObject.ViewObjectActivity
import java.util.*


class FragmentBuscador : Fragment(){

    private var _binding: FragmentBuscadorBinding? = null
    private val binding get() = _binding!!
    lateinit var baseDatos: AppBaseDatos
    private var adaptador:AdaptadorPublicacion = AdaptadorPublicacion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentBuscadorBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos = Room.databaseBuilder(view.context, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        binding.rvLista.setHasFixedSize(true)
        binding.rvLista.layoutManager = LinearLayoutManager(view.context)
        adaptador.AdaptadorPublicacion(baseDatos.publicacionDao.buscarPublicacion(), view.context)
        binding.rvLista.adapter = adaptador

    }


}
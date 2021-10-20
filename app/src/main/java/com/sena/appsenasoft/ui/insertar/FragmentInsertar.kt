package com.sena.appsenasoft.ui.insertar

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.sena.appsenasoft.ControladorImagenPubli
import com.sena.appsenasoft.ControladorImagenServ
import com.sena.appsenasoft.bd.AppBaseDatos
import com.sena.appsenasoft.bd.entitys.Publicacion
import com.sena.appsenasoft.bd.entitys.Servicios
import com.sena.appsenasoft.bd.entitys.Ubicacion
import com.sena.appsenasoft.databinding.FragmentInsertarBinding

class FragmentInsertar : Fragment() {

    private var _binding: FragmentInsertarBinding? = null
    private val binding get() = _binding!!
    lateinit var baseDatos: AppBaseDatos
    private var SELECT_DATO = 23
    private var SELECT_DATOTWO = 30
    private var imageUri: Uri? = null
    private var imageUriDos: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentInsertarBinding.inflate(inflater)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseDatos= Room.databaseBuilder(view.context, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        binding.ivImagenRegis.setOnClickListener {
            ControladorImagenPubli.seleccionarImagenGaleria(this, SELECT_DATO)
        }
        binding.ivImagenServ.setOnClickListener {
            ControladorImagenServ.seleccionarImagenGaleria(this, SELECT_DATOTWO)
        }
        binding.btnPublicacion.setOnClickListener {
            insertarUbi()
            insertarPubli()
        }
        binding.btnServicio.setOnClickListener {
            insertarServ()
        }
    }

    private fun insertarServ() {
        val servicios = Servicios(
            0,
            binding.etNombreServ.text.toString(),
            binding.etDescripcionSer.text.toString(),
            binding.etValor.text.toString(),
            binding.etIdPublicacion.text.toString().toInt()
        )
        val id = baseDatos.serviciosDao.insertarServicio(servicios)[0]
        imageUriDos?.let{
            ControladorImagenServ.guardarImagenes(requireView().context, id, it)
        }
        Toast.makeText(view?.context, "Se registro correctamenta", Toast.LENGTH_SHORT).show()
    }

    private fun insertarUbi(){
        val ubicacion = Ubicacion(
            0,
            1231.2,
            24124.2
        )
        baseDatos.ubicacionDao.insertarUbicacion(ubicacion)
        Toast.makeText(context, "Se inserto correctamente", Toast.LENGTH_SHORT).show()
    }
    private fun insertarPubli() {
        val publicacion = Publicacion(
            0,
            binding.etNombre.text.toString(),
            binding.etDescripcionRe.text.toString(),
            1,
            1
        )
        val id = baseDatos.publicacionDao.insertartPublicacion(publicacion)[0]
        imageUri?.let{
            ControladorImagenPubli.guardarImagenes(requireView().context, id, it)
        }
        Toast.makeText(view?.context, "Se añadio con exito", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when{
            requestCode == SELECT_DATO && resultCode == Activity.RESULT_OK ->{
                imageUri=data!!.data

                binding.ivImagenRegis.setImageURI(imageUri)
            }
            requestCode == SELECT_DATOTWO && resultCode == Activity.RESULT_OK -> {
                imageUriDos=data!!.data

                binding.ivImagenServ.setImageURI(imageUriDos)
            }
        }
    }

}
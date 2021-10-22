package com.sena.quehaypahacer.ui.insertar

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
import com.sena.quehaypahacer.ControladorImagenPubli
import com.sena.quehaypahacer.ControladorImagenServ
import com.sena.quehaypahacer.databinding.FragmentInsertarBinding
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.bd.entitys.Publicacion
import com.sena.quehaypahacer.bd.entitys.Servicios
import com.sena.quehaypahacer.bd.entitys.Ubicacion
import kotlinx.android.synthetic.main.fragment_insertar.*

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
        if(binding.etNombreServ.text.isEmpty() || binding.etDescripcionSer.text.isEmpty() || binding.etValor.text.isEmpty() || binding.etIdPublicacion.text.isEmpty()){
            Toast.makeText(view?.context, "Algun campo esta vacio, porfavor llenarlo", Toast.LENGTH_SHORT).show()
        }else{
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
            etNombreServ.text.clear()
            etDescripcionSer.text.clear()
            etValor.text.clear()
            etIdPublicacion.text.clear()
            Toast.makeText(view?.context, "Se registro correctamenta", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertarUbi(){
        val ubicacion = Ubicacion(
            0,
            1231.2,
            24124.2
        )
        baseDatos.ubicacionDao.insertarUbicacion(ubicacion)
    }
    private fun insertarPubli() {
        if(binding.etNombre.text.isEmpty() || binding.etDescripcionRe.text.isEmpty()){
            Toast.makeText(view?.context, "Algun campo esta vacio, porfavor llenarlo", Toast.LENGTH_SHORT).show()
        }else{
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
            binding.etNombre.text.clear()
            binding.etDescripcionRe.text.clear()
            Toast.makeText(view?.context, "Se añadio con exito", Toast.LENGTH_SHORT).show()
        }
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
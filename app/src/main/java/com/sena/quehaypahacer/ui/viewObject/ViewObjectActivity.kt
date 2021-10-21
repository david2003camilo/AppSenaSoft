package com.sena.quehaypahacer.ui.viewObject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sena.quehaypahacer.ControladorImagenPubli
import com.sena.quehaypahacer.R
import com.sena.quehaypahacer.adaptadores.AdaptadorPublicacion
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.bd.entitys.Historial
import com.sena.quehaypahacer.ui.ControladorImagenHistorial
import com.sena.quehaypahacer.ui.information.ContactFragment
import com.sena.quehaypahacer.ui.information.InformationObjectFragment

class ViewObjectActivity : AppCompatActivity() {

    lateinit var  btnAgregarBtn:Button
    lateinit var buttonService:Button
    lateinit var buttonContact:Button
    lateinit var descripcions:TextView
    lateinit var imagens:ImageView
    lateinit var nombre:TextView
    var fragmentInformation = InformationObjectFragment()
    var fragmentContact = ContactFragment()
    lateinit var baseDatos: AppBaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sena.quehaypahacer.R.layout.activity_view_object)
        buttonService = findViewById(R.id.buttonServices)
        buttonContact = findViewById(R.id.buttonContant)
        btnAgregarBtn = findViewById(R.id.btnAgregar)
        nombre = findViewById(R.id.tittle_object)
        descripcions = findViewById(R.id.tvDescripcionVi)
        imagens = findViewById(R.id.ivLogoVi)
        val bundle: Bundle?= intent.extras
        val nombres = bundle!!.getString("nombre")
        val descripciones = bundle.getString("descripcion")
        val imagen = bundle.getInt("imagenId")
        val imagenUri = ControladorImagenPubli.getImagenes(this, imagen.toLong())
        nombre.text = nombres
        descripcions.text = descripciones
        imagens.setImageURI(imagenUri)

        btnAgregarBtn.setOnClickListener {
            val historial = Historial(
                0,
                nombre.text.toString(),
                descripcions.text.toString()
            )
            val id = baseDatos.historialDao.insertarHistorial(historial)[0]
            imagenUri.let {
                ControladorImagenHistorial.guardarImagenes(this, id, it)
            }
        }
    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val trasaction = supportFragmentManager.beginTransaction()
            trasaction.replace(R.id.fragments_replace,fragment)
            trasaction.commit()
        }

    }
    fun onClick(view:View) {
        when (view.getId()) {
             R.id.buttonServices ->replaceFragment(fragmentInformation)
            R.id.buttonContant->replaceFragment(fragmentContact)
        }
    }
}
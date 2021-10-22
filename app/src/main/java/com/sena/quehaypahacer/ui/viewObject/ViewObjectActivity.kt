package com.sena.quehaypahacer.ui.viewObject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView
import com.sena.quehaypahacer.ControladorImagenPubli
import com.sena.quehaypahacer.R
import com.sena.quehaypahacer.adaptadores.AdaptadorPublicacion
import com.sena.quehaypahacer.adaptadores.AdapterHistorial
import com.sena.quehaypahacer.bd.AppBaseDatos
import com.sena.quehaypahacer.bd.entitys.Historial
import com.sena.quehaypahacer.ui.ControladorImagenHistorial
import com.sena.quehaypahacer.ui.FragmentListaServicios
import com.sena.quehaypahacer.ui.information.ContactFragment
import com.sena.quehaypahacer.ui.information.InformationObjectFragment
import com.sena.quehaypahacer.ui.information.MapActivity

class ViewObjectActivity : AppCompatActivity() {

    lateinit var  btnAgregarBtn:Button
    lateinit var buttonService:Button
    lateinit var buttonContact:Button
    lateinit var descripcions:TextView
    lateinit var imagens:ImageView
    var id:Int=0
    lateinit var fragmentListaServicios: FragmentListaServicios
    lateinit var nombre:TextView
    var fragmentContact = ContactFragment()
    lateinit var baseDatos: AppBaseDatos
    private var adaptador: AdapterHistorial = AdapterHistorial()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sena.quehaypahacer.R.layout.activity_view_object)
        buttonService = findViewById(R.id.buttonServices)
        buttonContact = findViewById(R.id.buttonContant)
        btnAgregarBtn = findViewById(R.id.btnAgregar)
        nombre = findViewById(R.id.tittle_object)
        descripcions = findViewById(R.id.tvDescripcionVi)
        imagens = findViewById(R.id.ivLogoVi)
        baseDatos = Room.databaseBuilder(this, AppBaseDatos::class.java, AppBaseDatos.DATABASE_NAME).allowMainThreadQueries().build()
        // Obtain BannerView.
        var bannerView: BannerView? = findViewById(R.id.hw_banner_view)
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView!!.adId = "testw6vs28auh3"
        bannerView!!.bannerAdSize = BannerAdSize.BANNER_SIZE_360_57
        // Set the refresh interval to 30 seconds.
        bannerView!!.setBannerRefresh(30)
        // Create an ad request to load an ad.
        val adParam = AdParam.Builder().build()
        bannerView!!.loadAd(adParam)

        val bundle: Bundle?= intent.extras
        val nombres = bundle!!.getString("nombre")
        val descripciones = bundle.getString("descripcion")
        val imagen = bundle.getInt("imagenId")
         id = bundle.getInt("id")
        Log.e("inof","$id")

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
             R.id.buttonServices ->{
                 fragmentListaServicios= FragmentListaServicios(id)
                 replaceFragment(fragmentListaServicios)}
            R.id.buttonContant->getViewObject()
        }
    }

    private fun getViewObject() {
        var intent = Intent(this,MapActivity::class.java)
        startActivity(intent)
}
}
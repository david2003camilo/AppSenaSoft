package com.sena.appsenasoft.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sena.appsenasoft.ControladorImagenPubli
import com.sena.appsenasoft.MainActivity
import com.sena.appsenasoft.R
import com.sena.appsenasoft.bd.entitys.Publicacion
import com.sena.appsenasoft.ui.viewObject.ViewObjectActivity


class AdaptadorPublicacion : RecyclerView.Adapter<AdaptadorPublicacion.ViewHolder>(){
    private var listaPublicacion: MutableList<Publicacion> = arrayListOf()
    lateinit var context: Context

    fun AdaptadorPublicacion(listaPublicacion: MutableList<Publicacion>, context: Context){
        this.listaPublicacion=listaPublicacion
        this.context=context
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val nombre = view.findViewById(R.id.tvNombreVis) as TextView
        val descripcion = view.findViewById(R.id.tvDescripcion) as TextView
        val imagen = view.findViewById(R.id.ivImagen) as ImageView
        fun bind(publicacion: Publicacion, context: Context){
            nombre.text = publicacion.nombre
            descripcion.text = publicacion.descripcion
            val imagenUri = ControladorImagenPubli.getImagenes(context, publicacion.id.toLong())
            imagen.setImageURI(imagenUri)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layout = LayoutInflater.from(context)
        return ViewHolder(
            layout.inflate(R.layout.lista, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val lista = listaPublicacion[position]
        holder.bind(lista, context)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Mensaje", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return listaPublicacion.size
    }
}
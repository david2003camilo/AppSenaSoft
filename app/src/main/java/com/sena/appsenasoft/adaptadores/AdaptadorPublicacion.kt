package com.sena.appsenasoft.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.appsenasoft.R
import com.sena.appsenasoft.bd.entitys.Publicacion


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
        fun bind(publicacion: Publicacion, context: Context){
            nombre.text = publicacion.nombre
            descripcion.text = publicacion.descripcion
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
    }

    override fun getItemCount(): Int {
        return listaPublicacion.size
    }
}
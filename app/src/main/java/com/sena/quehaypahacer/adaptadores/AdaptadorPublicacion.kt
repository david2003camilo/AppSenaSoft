package com.sena.quehaypahacer.adaptadores

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sena.quehaypahacer.ControladorImagenPubli
import com.sena.quehaypahacer.bd.entitys.Publicacion
import com.sena.quehaypahacer.R
import com.sena.quehaypahacer.ui.buscador.FragmentBuscador
import com.sena.quehaypahacer.ui.viewObject.ViewObjectActivity
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList


class AdaptadorPublicacion : RecyclerView.Adapter<AdaptadorPublicacion.ViewHolder>(){

    private var listaPublicacion: MutableList<Publicacion> = arrayListOf()
    lateinit var context: Context

    fun AdaptadorPublicacion(listaPublicacion: MutableList<Publicacion>, context: Context){
        this.listaPublicacion=listaPublicacion
        this.context=context
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
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
            val intent: Intent = Intent(holder.itemView.context, ViewObjectActivity::class.java)
            intent.putExtra("nombre", lista.nombre)
            intent.putExtra("descripcion", lista.descripcion)
            intent.putExtra("imagenId", lista.id)
            startActivity(context, intent, bundleOf())
        }
    }

    override fun getItemCount(): Int {
        return listaPublicacion.size
    }

}
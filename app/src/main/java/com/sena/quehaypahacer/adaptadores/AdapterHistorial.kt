package com.sena.quehaypahacer.adaptadores

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.quehaypahacer.ControladorImagenPubli
import com.sena.quehaypahacer.R
import com.sena.quehaypahacer.bd.entitys.Historial
import com.sena.quehaypahacer.bd.entitys.Publicacion
import org.w3c.dom.Text

class AdapterHistorial:RecyclerView.Adapter<AdapterHistorial.ViewHolder>() {

    private var listaHistorial:MutableList<Historial> = arrayListOf()
    lateinit var context: Context

    fun AdapterHistorial(listaHistorial: MutableList<Historial>, context: Context){
        this.listaHistorial = listaHistorial
        this.context = context
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val nombre = view.findViewById(R.id.tvNombreRe) as TextView
        val descripcion = view.findViewById(R.id.tvDescripRe) as TextView
        val imagen = view.findViewById(R.id.ivImagenRe) as ImageView
        fun bind(context: Context, historial: Historial){
            nombre.text = historial.nombre
            descripcion.text = historial.descripcion
            val imageUir = ControladorImagenPubli.getImagenes(context, historial.id.toLong())
            imagen.setImageURI(imageUir)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(context)
        return ViewHolder(
            layout.inflate(R.layout.lista_historial, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lista = listaHistorial[position]
        holder.bind(context, lista)
    }

    override fun getItemCount(): Int {
        return listaHistorial.size
    }
}
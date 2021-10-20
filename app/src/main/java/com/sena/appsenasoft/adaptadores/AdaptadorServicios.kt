package com.sena.appsenasoft.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sena.appsenasoft.R
import com.sena.appsenasoft.bd.entitys.Servicios
import org.w3c.dom.Text

class AdaptadorServicios: RecyclerView.Adapter<AdaptadorServicios.ViewHolder>() {

    private var listaServicios:MutableList<Servicios> = arrayListOf()
    lateinit var context: Context

    fun AdaptadorServicios(listaServicios: MutableList<Servicios>, context: Context){
        this.listaServicios = listaServicios
        this.context = context
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nombre = view.findViewById(R.id.tvNombreServi) as TextView
        val ubicacion = view.findViewById(R.id.tvDescripcion) as TextView
        val valor = view.findViewById(R.id.tvValor) as TextView
        fun bind(servicios: Servicios, context: Context){
            nombre.text = servicios.nombreServicio
            ubicacion.text = servicios.descripcion
            valor.text = servicios.valor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(context)
        return  ViewHolder(
            layout.inflate(R.layout.lista_servicios, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lista = listaServicios[position]
        holder.bind(lista, context)
    }

    override fun getItemCount(): Int {
        return  listaServicios.size
    }
}
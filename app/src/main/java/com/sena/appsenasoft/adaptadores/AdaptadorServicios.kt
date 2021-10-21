package com.sena.quehaypahacer.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sena.quehaypahacer.bd.entitys.Servicios
import com.sena.quehaypahacer.R
import org.w3c.dom.Text
import android.content.Intent as Intent

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
package com.sena.quehaypahacer.ui

import android.content.Context
import android.net.Uri
import java.io.File

object ControladorImagenHistorial {
    fun guardarImagenes(context: Context, id:Long, uri: Uri){
        val archivo = File(context.filesDir, id.toString())
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!
        archivo.writeBytes(bytes)
    }
    fun getImagenes(context: Context, id:Long):Uri{
        val archivo = File(context.filesDir, id.toString())
        return if(archivo.exists()) Uri.fromFile(archivo)
        else Uri.parse("android.resource://com.example.images/drawable/ic_dashboard_black_24dp")
    }
}
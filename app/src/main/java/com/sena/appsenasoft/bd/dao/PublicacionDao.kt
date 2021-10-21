package com.sena.quehaypahacer.bd.dao

import androidx.room.*
import com.sena.quehaypahacer.bd.entitys.Publicacion

@Dao
interface PublicacionDao {
    @Insert
    fun insertartPublicacion(vararg publicacion: Publicacion):List<Long>
    @Update
    fun actualizarPublicacion(publicacion: Publicacion)
    @Delete
    fun eliminarPublicacion(publicacion: Publicacion)
    @Query("SELECT * FROM publicacion")
    fun buscarPublicacion():MutableList<Publicacion>
}
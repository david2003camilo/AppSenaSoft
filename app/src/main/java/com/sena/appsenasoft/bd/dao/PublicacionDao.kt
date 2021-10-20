package com.sena.appsenasoft.bd.dao

import androidx.room.*
import com.sena.appsenasoft.bd.entitys.Publicacion

@Dao
interface PublicacionDao {
    @Insert
    fun insertartPublicacion(publicacion: Publicacion)
    @Update
    fun actualizarPublicacion(publicacion: Publicacion)
    @Delete
    fun eliminarPublicacion(publicacion: Publicacion)
    @Query("SELECT * FROM publicacion")
    fun buscarPublicacion():MutableList<Publicacion>
}
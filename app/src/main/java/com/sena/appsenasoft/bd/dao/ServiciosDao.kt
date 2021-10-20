package com.sena.appsenasoft.bd.dao

import androidx.room.*
import com.sena.appsenasoft.bd.entitys.Servicios

@Dao
interface ServiciosDao {
    @Insert
    fun insertarServicio(vararg servicios: Servicios):List<Long>
    @Update
    fun actualizarServicio(servicios: Servicios)
    @Delete
    fun eliminarServicio(servicios: Servicios)
    @Query("SELECT * FROM servicios")
    fun buscarServicios():MutableList<Servicios>
}
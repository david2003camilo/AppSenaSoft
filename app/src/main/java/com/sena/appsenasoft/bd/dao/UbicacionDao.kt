package com.sena.appsenasoft.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import com.sena.appsenasoft.bd.entitys.Ubicacion

@Dao
interface UbicacionDao {
    @Insert
    fun insertarUbicacion(ubicacion: Ubicacion)
}
package com.sena.quehaypahacer.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import com.sena.quehaypahacer.bd.entitys.Ubicacion

@Dao
interface UbicacionDao {
    @Insert
    fun insertarUbicacion(ubicacion: Ubicacion)
}
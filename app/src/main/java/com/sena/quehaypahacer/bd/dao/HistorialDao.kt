package com.sena.quehaypahacer.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sena.quehaypahacer.bd.entitys.Historial

@Dao
interface HistorialDao {
    @Insert
    fun insertarHistorial(vararg historial: Historial): List<Long>
    @Query("SELECT * FROM historial")
    fun buscarHistorial():MutableList<Historial>
}
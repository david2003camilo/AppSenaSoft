package com.sena.quehaypahacer.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sena.quehaypahacer.bd.entitys.Usuarios

@Dao
interface UsuarioDao {
    @Insert
    fun insertarUsuario(usuarios: Usuarios)
}
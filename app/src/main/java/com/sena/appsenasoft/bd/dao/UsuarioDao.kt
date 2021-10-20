package com.sena.appsenasoft.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import com.sena.appsenasoft.bd.entitys.Usuarios

@Dao
interface UsuarioDao {
    @Insert
    fun insertarUsuario(usuarios: Usuarios)
}
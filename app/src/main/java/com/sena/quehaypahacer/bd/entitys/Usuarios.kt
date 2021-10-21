package com.sena.quehaypahacer.bd.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuarios (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idUsuario")
    val idUsuario:Int = 0,
    @ColumnInfo(name="email")
    val email:String,
    @ColumnInfo(name="password")
    var password:String,
    @ColumnInfo(name="passwordTwo")
    var passwordTwo:String,
    @ColumnInfo(name="tipo")
    var tipo:String,
    @ColumnInfo(name="username")
    var username:String
        )
package com.sena.quehaypahacer.bd.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial")
data class Historial (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id:Int = 0,
    @ColumnInfo(name="nombre")
    var nombre:String,
    @ColumnInfo(name="descripcion")
    var descripcion:String
    )
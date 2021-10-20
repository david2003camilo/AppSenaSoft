package com.sena.appsenasoft.bd.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ubicacion")
data class Ubicacion (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idUbicacion")
    val id:Int = 0,
    @ColumnInfo(name="longitud")
    var longitud:Double,
    @ColumnInfo(name="latitud")
    var latitud:Double
)
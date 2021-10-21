package com.sena.quehaypahacer.bd.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "servicios",
        foreignKeys = [
            ForeignKey(entity = Publicacion::class, parentColumns = ["idPublicacion"], childColumns = ["idPublicacionFK"])
        ]
    )
data class Servicios(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id:Int = 0,
    @ColumnInfo(name="nombreServicio")
    var nombreServicio:String,
    @ColumnInfo(name="descripcion")
    var descripcion:String,
    @ColumnInfo(name="valor")
    var valor: String,
    @ColumnInfo(name="idPublicacionFK")
    var idPublicacionFk:Int
        )
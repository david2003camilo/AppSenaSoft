package com.sena.quehaypahacer.bd.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName="publicacion",
        foreignKeys=[
            ForeignKey(entity=Usuarios::class, parentColumns = ["idUsuario"], childColumns = ["idUsuarioFK"]),
            ForeignKey(entity=Ubicacion::class, parentColumns = ["idUbicacion"], childColumns = ["idUbicacionFK"])
        ]
    )
data class Publicacion (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idPublicacion")
    val id:Int=0,
    @ColumnInfo(name="nombre")
    var nombre:String,
    @ColumnInfo(name="descripcion")
    var descripcion:String,
    @ColumnInfo(name="idUsuarioFK")
    var idUsuarioFk:Int,
    @ColumnInfo(name="idUbicacionFK")
    var idUbicacionFk:Int
        )
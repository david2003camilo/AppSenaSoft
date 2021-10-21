package com.sena.quehaypahacer.bd

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sena.quehaypahacer.bd.dao.*
import com.sena.quehaypahacer.bd.entitys.*

@Database(entities = [Historial::class, Publicacion::class, Servicios::class, Ubicacion::class, Usuarios::class], version=4, exportSchema = false)
abstract class AppBaseDatos:RoomDatabase() {
    abstract val historialDao:HistorialDao
    abstract val publicacionDao:PublicacionDao
    abstract val serviciosDao:ServiciosDao
    abstract val ubicacionDao:UbicacionDao
    abstract val usuarioDao:UsuarioDao

    companion object{
        const val DATABASE_NAME = "QueHayQueHacer"
    }
}
package com.example.projetobd

import android.content.Context
import androidx.room.Database
import  androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


@Database(entities = [jogador::class, time::class], exportSchema =  false , version = 2)
abstract class BaseDados : RoomDatabase() {
    abstract fun getMeuCrudJogador() : crudJogadores
    abstract fun getMeuCrudTime() : crudTime

    companion object {
        var INSTANCE: BaseDados? = null
        fun getInstance(context: Context): BaseDados {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDados::class.java,
                    "fute.bd")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE !!
        }
    }
}
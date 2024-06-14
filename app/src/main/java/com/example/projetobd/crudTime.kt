package com.example.projetobd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projetobd.time

@Dao
interface crudTime {
    @Insert
    fun inserir(objCliente : time)

    @Update
    fun Alterar(objCliente : time)

    @Query("SELECT * FROM time")
    fun Pesquisar()  : List<time>



    @Delete
    fun Deletar(objCliente : time)


    @Query("delete from time")
    fun DeletarTudo()

}
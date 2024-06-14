package com.example.projetobd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.projetobd.jogador

@Dao
interface crudJogadores {
    @Insert
    fun inserir(jogadorvar: jogador)

    @Update
    fun Alterar(jogadorvar : jogador)

    @Query("SELECT * FROM jogador")
    fun Pesquisar() : List<jogador>

    @Delete
    fun Deletar(jogadorvar : jogador)


    @Query("delete from jogador")
    fun DeletarTudo()


}
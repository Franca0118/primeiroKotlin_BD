package com.example.projetobd


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext


class AdaptadorJogadorCard(NovaLista: List<ClassJogadorAux>, tela32: Activity, isJogador: Boolean): RecyclerView.Adapter<ClassCardJogador>() {


        class ClassJogadorAux(
            var imgJogador : String,
            var nome : String,
            var time : String,
            var camisa: String
        )


    var JogadorLista : List<ClassJogadorAux> = NovaLista
    var tela : Activity = tela32
    var isJogadorAux = isJogador





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassCardJogador {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.cardjogador, parent, false)
        return ClassCardJogador(view)
    }

    override fun getItemCount(): Int {
        return JogadorLista.size
    }

    override fun onBindViewHolder(holder: ClassCardJogador, position: Int) {


        if (isJogadorAux)
        {
            holder.NomeJogadorCard.text = JogadorLista[position].nome
            holder.TimeJogadorCard.text = JogadorLista[position].time
            holder.CamisaJogadorCard.text = JogadorLista[position].camisa
            var imgagem = holder.ImgagemJogador

            try {
                Glide.with(tela)
                    .load("${JogadorLista[position].imgJogador}")
                    .into(imgagem)
            } catch (erro: ArithmeticException) {
                Toast.makeText(tela, "ERRO NA IMGAGEM $position", Toast.LENGTH_SHORT).show()
            }
        }else {
            holder.NomeJogadorCard.text = JogadorLista[position].nome
            holder.TimeJogadorCard.text = JogadorLista[position].time
            holder.CamisaJogadorCard.text = JogadorLista[position].camisa


            var imgagem = holder.ImgagemJogador
            imgagem.visibility = View.INVISIBLE
            imgagem.isEnabled = false
        }




    }




}
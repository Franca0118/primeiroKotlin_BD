package com.example.projetobd

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClassCardJogador(view: View) : RecyclerView.ViewHolder(view){

    var NomeJogadorCard : TextView = view.findViewById(R.id.txtNomeCard)
    var CamisaJogadorCard : TextView = view.findViewById(R.id.txtCamisaCard)
    var TimeJogadorCard : TextView = view.findViewById(R.id.txtTimeCard)
    var ImgagemJogador : ImageView = view.findViewById(R.id.imgJogadorCard)
}
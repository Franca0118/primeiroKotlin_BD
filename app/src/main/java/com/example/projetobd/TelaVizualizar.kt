package com.example.projetobd

import android.content.Intent
import android.hardware.TriggerEvent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class TelaVizualizar : AppCompatActivity() {
    lateinit var BotaoTime : Button
    lateinit var BotaoJogador : Button
    lateinit var SpinenrTime : Spinner
    lateinit var MeuRecyclerView : RecyclerView
    lateinit var  botaoPesquisarSpinner : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_vizualizar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        botaoPesquisarSpinner = findViewById(com.example.projetobd.R.id.btnPesquisar)
        MeuRecyclerView = findViewById(R.id.recicladorJogadores)
        var BotaoVoltar : Button = findViewById(R.id.btnVoltar)
        BotaoTime = findViewById(R.id.btnTimevz)
        BotaoJogador = findViewById(R.id.btnJogadorvz)
        SpinenrTime = findViewById(R.id.spinner)
        //Criacao do novo adapdador com valores do banco

        BotaoVoltar.setOnClickListener{ Voltar()  }
        BotaoTime.setOnClickListener{BotaoTime()}
        BotaoJogador.setOnClickListener{BotaoJogador()}

        var MeuArrayDeTImes = mutableListOf("Sem time ;(")
        GlobalScope.launch(Dispatchers.IO){
            val TimesParaColocar = BaseDados.getInstance(this@TelaVizualizar).getMeuCrudTime().Pesquisar()
            for (i in TimesParaColocar)
            {
                MeuArrayDeTImes.add(i.nome)
            }
        }

        MeuRecyclerView.adapter = AdaptadorJogadorCard(prepararDados(true), this@TelaVizualizar, true)
        MeuRecyclerView.layoutManager = LinearLayoutManager(this)
        val AdaptadorDoSpinner  = ArrayAdapter(this, android.R.layout.simple_spinner_item, MeuArrayDeTImes)
        SpinenrTime.adapter = AdaptadorDoSpinner


        botaoPesquisarSpinner.setOnClickListener{SelecionarPorTime()}



    }




    fun Voltar(){
       startActivity(Intent(this, menu::class.java))
    }


    fun SelecionarPorTime()
    {

        val ListaAux : MutableList<AdaptadorJogadorCard.ClassJogadorAux> = mutableListOf()
        GlobalScope.launch(Dispatchers.IO) {

            var CrudJogador = BaseDados.getInstance(this@TelaVizualizar).getMeuCrudJogador()
            val ListaTodosJogadores = CrudJogador.Pesquisar()

            for (i in ListaTodosJogadores)
            {
                if (i.time == SpinenrTime.selectedItem.toString())
                {
                    var listaAuxAux = AdaptadorJogadorCard.ClassJogadorAux("${i.imgurl}","${i.nome}","${i.camisa}", "${i.time}")
                    ListaAux.add(listaAuxAux)
                }
            }
        }



        MeuRecyclerView.adapter = AdaptadorJogadorCard(ListaAux, this@TelaVizualizar, true)
        MeuRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    fun prepararDados(isJogador : Boolean): MutableList<AdaptadorJogadorCard.ClassJogadorAux>{
        if (isJogador)
        {
            val ListaAux : MutableList<AdaptadorJogadorCard.ClassJogadorAux> = mutableListOf()
            GlobalScope.launch(Dispatchers.IO) {

                var CrudJogador = BaseDados.getInstance(this@TelaVizualizar).getMeuCrudJogador()
                val ListaTodosJogadores = CrudJogador.Pesquisar()

                for (i in ListaTodosJogadores)
                {
                    var listaAuxAux = AdaptadorJogadorCard.ClassJogadorAux("${i.imgurl}","${i.nome}","${i.camisa}", "${i.time}")
                    ListaAux.add(listaAuxAux)
                }
            }

            return ListaAux
        } else{
            val ListaAux : MutableList<AdaptadorJogadorCard.ClassJogadorAux> = mutableListOf()
            GlobalScope.launch(Dispatchers.IO) {
                // TESTAR SE O BANCO DE DADOS ESTA ABERTO
                var CrudJogador = BaseDados.getInstance(this@TelaVizualizar).getMeuCrudTime()
                val ListaTodosJogadores = CrudJogador.Pesquisar()

                for (i in ListaTodosJogadores)
                {
                    var listaAuxAux = AdaptadorJogadorCard.ClassJogadorAux("nd","${i.nome}","${i.local}", "${i.dono}")
                    ListaAux.add(listaAuxAux)
                }
            }

            return ListaAux
        }


    }


    fun BotaoTime(){
        botaoPesquisarSpinner.isEnabled = false
        botaoPesquisarSpinner.visibility = View.INVISIBLE
        SpinenrTime.isEnabled = false
        SpinenrTime.visibility = View.INVISIBLE


        MeuRecyclerView.adapter = AdaptadorJogadorCard(prepararDados(false), this@TelaVizualizar, false)
        MeuRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun BotaoJogador(){
        botaoPesquisarSpinner.isEnabled = true
        botaoPesquisarSpinner.visibility = View.VISIBLE
        SpinenrTime.isEnabled = true
        SpinenrTime.visibility = View.VISIBLE


        MeuRecyclerView.adapter = AdaptadorJogadorCard(prepararDados(true), this@TelaVizualizar, true)
        MeuRecyclerView.layoutManager = LinearLayoutManager(this)
    }



}
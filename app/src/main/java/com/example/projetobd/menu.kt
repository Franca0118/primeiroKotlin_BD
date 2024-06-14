package com.example.projetobd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var BotaoCadastrarJogador : Button = findViewById(R.id.cadastrarJogador)
        var BotaoVizualizar : Button = findViewById(R.id.vizualizar)
        var BotaoApagar : Button = findViewById(R.id.apagarTudo)
        var BotaoCadastrarTime : Button = findViewById(R.id.cadastrarTime)
        BotaoApagar.setOnClickListener{ apagarTodosOsDados() }
        BotaoVizualizar.setOnClickListener{ MostrarTodosOsJogadores() }
        BotaoCadastrarJogador.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        BotaoCadastrarTime.setOnClickListener{
            startActivity(Intent(this, cadastroDeTimeAC::class.java))
        }
    }



    fun apagarTodosOsDados(){
        GlobalScope.launch(Dispatchers.IO) {
            BaseDados.getInstance(this@menu).getMeuCrudJogador().DeletarTudo()
            BaseDados.getInstance(this@menu).getMeuCrudTime().DeletarTudo()
        }
        Toast.makeText(applicationContext, "sucesso, tabelas limpas", Toast.LENGTH_SHORT).show()
    }

    fun MostrarTodosOsJogadores(){
        startActivity(Intent( this, TelaVizualizar::class.java ))
    }



}
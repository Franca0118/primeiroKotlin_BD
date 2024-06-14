package com.example.projetobd

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var nometxt : EditText
    lateinit var contradotxt : Spinner
    lateinit var camisatxt : EditText
    lateinit var buttonCriar : Button
    lateinit var imgUrl : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        BaseDados.getInstance(this@MainActivity)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgUrl = findViewById(R.id.txturl)
        nometxt = findViewById(R.id.txtNomeTime)
        // Codigo para o spinner funcionar
        contradotxt = findViewById(R.id.txtTime)


        var MeuArrayDeTImes = mutableListOf("Sem time ;(")
        GlobalScope.launch(Dispatchers.IO){
          val TimesParaColocar = BaseDados.getInstance(this@MainActivity).getMeuCrudTime().Pesquisar()
           for (i in TimesParaColocar)
           {
               MeuArrayDeTImes.add(i.nome.toString())
           }
        }


        val AdaptadorDoSpinner  = ArrayAdapter(this, android.R.layout.simple_spinner_item, MeuArrayDeTImes)
        contradotxt.adapter = AdaptadorDoSpinner
        camisatxt = findViewById(R.id.txtDono)
        buttonCriar = findViewById(R.id.botaoCriar)








        var ImgameMenu : ImageView = findViewById(R.id.ImgamBase)

        Glide.with(this)
            .load("https://sportsjob.com.br/wp-content/uploads/2020/11/futebol.jpg")
            .into(ImgameMenu)

        buttonCriar.setOnClickListener{ gravarDados() }
        var botao : Button = findViewById(R.id.botaoVoltar)
        botao.setOnClickListener{
            startActivity(Intent(this, menu::class.java))
        }

    }

    fun gravarDados(){
        if ( nometxt.text.toString() != "" && camisatxt.text.toString() != "" &&  imgUrl.text.toString() != "")
        {
            val cc = jogador(null, nometxt.text.toString(), camisatxt.text.toString(), contradotxt.selectedItem.toString(), imgUrl.text.toString())
            GlobalScope.launch(Dispatchers.IO) {
                BaseDados.getInstance(this@MainActivity).getMeuCrudJogador().inserir(cc)
            }
            Toast.makeText(applicationContext, "SUCESSO, CRIADO NO BANCO", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, menu::class.java))
        } else
        {
            Toast.makeText(applicationContext, "VALORES VAZIOS", Toast.LENGTH_SHORT).show()
        }

    }




    

}

package com.example.projetobd

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class cadastroDeTimeAC : AppCompatActivity() {
    lateinit var NomeDotime : EditText
    lateinit var Donodotime : EditText
    lateinit var Localdotime: EditText
    lateinit var buttonCriar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        BaseDados.getInstance(this@cadastroDeTimeAC)
        setContentView(R.layout.activity_cadastro_de_time)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        Localdotime = findViewById(R.id.txtLocal)
        NomeDotime = findViewById(R.id.txtNomeTime)
        Donodotime = findViewById(R.id.txtDono)
        buttonCriar = findViewById(R.id.botaoCriar)




        var ImgameMenu : ImageView = findViewById(R.id.ImgamBase)
        Glide.with(this)
            .load("https://cdn.discordapp.com/attachments/1202014526116216892/1250904416316424202/image.png?ex=666ca305&is=666b5185&hm=fab4754075998b4dfedaa9e725c480c9916ff01a0e997f16f9f6d3229e299122&")
            .into(ImgameMenu)

        buttonCriar.setOnClickListener{ gravarDados() }




        var botao : Button = findViewById(R.id.botaoVoltar)
        botao.setOnClickListener{
            startActivity(Intent(this, menu::class.java))
        }



    }

   fun gravarDados(){
        if ( NomeDotime.text.toString() != "" && Localdotime.text.toString() != "" &&  Donodotime.text.toString() != "")
        {
            val cc = time(null, NomeDotime.text.toString(), Localdotime.text.toString(), Donodotime.text.toString() )
            GlobalScope.launch(Dispatchers.IO) {
                BaseDados.getInstance(this@cadastroDeTimeAC).getMeuCrudTime().inserir(cc)
            }


            Toast.makeText(applicationContext, "SUCESSO, CRIADO NO BANCO", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, menu::class.java))
        } else
        {
            Toast.makeText(applicationContext, "VALORES VAZIOS", Toast.LENGTH_SHORT).show()
        }

    }
}
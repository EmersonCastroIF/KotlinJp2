package com.example.a07ex03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa os botões flutuantes
        val botaoNavegador = findViewById<FloatingActionButton>(R.id.browserButton)
        val botaoLigacao = findViewById<FloatingActionButton>(R.id.callButton)
        val botaoSMS = findViewById<FloatingActionButton>(R.id.smsButton)
        val botaoEmail = findViewById<FloatingActionButton>(R.id.emailButton)

        // Define as ações dos botões flutuantes
        botaoNavegador.setOnClickListener {
            exibirMensagemToast(this, "Abrindo o navegador!") // Exibir uma mensagem Toast com o texto fornecido
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
        }
        botaoLigacao.setOnClickListener {
            exibirMensagemToast(this, "Iniciando uma ligação!") // Exibir uma mensagem Toast com o texto fornecido
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789")))
        }
        botaoSMS.setOnClickListener {
            exibirMensagemToast(this, "Enviando um SMS!") // Exibir uma mensagem Toast com o texto fornecido
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("sms:123456789")))
        }
        botaoEmail.setOnClickListener {
            exibirMensagemToast(this, "Enviando um e-mail!") // Exibir uma mensagem Toast com o texto fornecido
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:example@example.com")))
        }
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

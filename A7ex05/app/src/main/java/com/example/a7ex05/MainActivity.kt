package com.example.a7ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontrar o ImageView no layout
        val imageView = findViewById<ImageView>(R.id.imgFoto)

        // Usar a biblioteca Glide para carregar e exibir uma imagem a partir de uma URL
        Glide.with(this)
            .load("https://static.itdg.com.br/images/1200-630/92ab14d076bc6dfcc72ed10712924cd1/320646-original.jpg")
            .into(imageView)

        // Chamar a função 'exibirMensagemToast' para mostrar um Toast
        exibirMensagemToast(this, "Bolo")
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    // Criar um Toast com a mensagem fornecida e exibi-lo na tela
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

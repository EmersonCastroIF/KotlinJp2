package com.example.a07ex06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chama a função 'exibirMensagemToast' para mostrar um Toast
        exibirMensagemToast(this, "Operação bem-sucedida")

        // Criando um array de strings com 100 itens numerados
        val itens = Array(100) { i -> "Item ${i + 1}" }

        // Configurando o ArrayAdapter com o contexto atual, layout padrão e o array de strings
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, itens)

        // Encontrando a ListView no layout
        val listView = findViewById<ListView>(R.id.listView)

        // Conectando o adaptador à ListView
        listView.adapter = adaptador
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    // Cria um Toast com a mensagem fornecida e exibe-o na tela
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

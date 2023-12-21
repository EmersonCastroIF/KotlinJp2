package com.example.a07ex07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontrar o RecyclerView no layout
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        // Configurar o layout manager para o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Criar uma lista de produtos
        val listaProdutos = ArrayList<Produto>()

        // Adicionar os dois primeiros produtos à lista
        listaProdutos.add(Produto(android.R.drawable.ic_menu_camera, "Máquina fotográfica", "R$ 500,00"))
        listaProdutos.add(Produto(android.R.drawable.ic_menu_call, "Telefone analógico", "R$ 90,00"))

        // Adicionar os demais produtos fictícios à lista
        for (i in 3..99) {
            listaProdutos.add(Produto(android.R.drawable.ic_menu_edit, "Produto $i", "R$ $i,99"))
        }

        // Criar e configurar o adaptador com a lista de produtos
        val adapter = CustomAdapter(listaProdutos)
        // Definir o adaptador para o RecyclerView
        recyclerView.adapter = adapter

        // Chamar a função 'exibirMensagemToast' para mostrar uma mensagem Toast
        exibirMensagemToast(this, "Carregamento finalizado!")
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

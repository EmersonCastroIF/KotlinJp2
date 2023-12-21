package com.example.a07ex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.core.widget.NestedScrollView
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define o layout da atividade com base no arquivo 'activity_main.xml'
        setContentView(R.layout.activity_main)
        // Chama a função 'exibirMensagemToast' para mostrar um Toast
        exibirMensagemToast(this, "Calendário!!")
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    // Criar um Toast com a mensagem fornecida e exibi-lo na tela
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

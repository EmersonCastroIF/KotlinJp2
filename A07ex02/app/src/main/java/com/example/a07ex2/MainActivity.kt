package com.example.a07ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtém uma referência ao layout principal da tela
        var layoutPrincipal = findViewById<ConstraintLayout>(R.id.meuLayout)

        // Define um evento de clique no layout (qualquer parte da tela)
        layoutPrincipal.setOnClickListener { view ->
            // Obtém uma referência ao TextView da tela
            var rotulo = findViewById<TextView>(R.id.textView)

            // Exibe um Snackbar curto
            Snackbar.make(view, "Mensagem Curta", Snackbar.LENGTH_SHORT).show()

            // Exibe um Snackbar longo
            Snackbar.make(view, "Mensagem Longa", Snackbar.LENGTH_LONG).show()

            // Exibe um Snackbar com a opção de fechar
            var snackbar = Snackbar.make(view, rotulo.text, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Fechar") { snackbar.dismiss() }
            snackbar.show()

            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "Parabéns, clicou na tela!") // Exibe um Toast com a mensagem fornecida
        }
    }
}

// Função para exibir um Toast na tela
fun exibirToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

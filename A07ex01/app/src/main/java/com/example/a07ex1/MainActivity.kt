package com.example.a07ex1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        // Define um listener de clique para o TextView
        textView.setOnClickListener {
            // Cria e exibe um Snackbar com o texto do TextView
            val snackBar = Snackbar.make(it, textView.text, Snackbar.LENGTH_LONG)
            snackBar.show()

            // Chama a função 'exibirToast' para exibir um Toast
            exibirToast(this, "Clicou!!!!")
        }
    }
}

// Função para exibir um Toast na tela
fun exibirToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

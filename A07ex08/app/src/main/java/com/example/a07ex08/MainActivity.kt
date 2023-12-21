package com.example.a07ex08

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Declaração de uma variável lateinit para armazenar um ImageView
    private lateinit var imageViewCapturada: ImageView

    // Objeto para lidar com o resultado da atividade de captura de imagem
    private val resultadoCaptura = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        // Verifica se a operação de captura de imagem foi bem-sucedida
        if (resultado.resultCode == RESULT_OK) {
            // Converte a imagem capturada para um objeto Bitmap
            val imagemBitmap = resultado.data?.extras?.get("data") as Bitmap
            // Exibe o Bitmap capturado no ImageView
            imageViewCapturada.setImageBitmap(imagemBitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa a variável imageViewCapturada com o ImageView do layout
        imageViewCapturada = findViewById(R.id.imageView_captured)

        // Obtém uma referência ao botão de captura do layout
        val botaoCaptura: Button = findViewById(R.id.button_capture)

        // Configura um ouvinte de clique para o botão de captura
        botaoCaptura.setOnClickListener {
            // Verifica se a permissão para usar a câmera não foi concedida
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Solicita permissão para usar a câmera se não foi concedida
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA), SOLICITACAO_PERMISSAO_CAMERA)
            } else {
                // Chama a função 'exibirMensagemToast' para exibir uma mensagem Toast
                exibirMensagemToast(this, "Capturou a imagem")
                // Inicia a captura de imagem
                capturarImagem()
            }
        }
    }

    // Função para iniciar a captura de imagem
    private fun capturarImagem() {
        // Cria uma intenção para a atividade de captura de imagem
        val intentCapturaImagem = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Inicia a atividade de captura de imagem e aguarda o resultado
        resultadoCaptura.launch(intentCapturaImagem)
    }

    companion object {
        // Constante para o código de solicitação de permissão da câmera
        private const val SOLICITACAO_PERMISSAO_CAMERA = 100
    }

    // Manipula a resposta da solicitação de permissão
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            SOLICITACAO_PERMISSAO_CAMERA -> {
                // Verifica se a permissão da câmera foi concedida
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Se a permissão foi concedida, inicia a captura de imagem
                    capturarImagem()
                } else {
                    // Código para informar ao usuário que a permissão foi negada
                }
                return
            }
            else -> {
                // Ignora todas as outras solicitações de permissão
            }
        }
    }
}

// Função para exibir uma mensagem Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

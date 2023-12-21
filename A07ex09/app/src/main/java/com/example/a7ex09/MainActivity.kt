package com.example.a7ex09

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var botaoCapturar: Button
    lateinit var imageViewFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoCapturar = findViewById(R.id.btnCapturar)
        imageViewFoto = findViewById(R.id.imgFoto)
    }

    // Objeto para registrar o evento de captura de foto
    val registrarCaptura = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { imagem: Bitmap? ->  // 'imagem' pode ser nula, por isso 'Bitmap?' é usado.
        imagem?.let { bitmap ->
            imageViewFoto.setImageBitmap(bitmap)  // 'bitmap' dentro do 'let' não é nulo.

            val imagemInput = InputImage.fromBitmap(bitmap, 0)
            val resultado = BarcodeScanning.getClient().process(imagemInput)
                .addOnSuccessListener { codigosDeBarras ->
                    for (codigoDeBarras in codigosDeBarras) {
                        val tipoDeValor = codigoDeBarras.valueType
                        when (tipoDeValor) {
                            Barcode.TYPE_URL -> {
                                codigoDeBarras.url?.let { url ->
                                    findViewById<TextView>(R.id.txtResultado).text = url.url
                                } ?: run {
                                    // Trate o caso onde a URL é nula.
                                    findViewById<TextView>(R.id.txtResultado).text = "URL não encontrada no código de barras."
                                }
                            }
                            // Você pode adicionar mais casos aqui para outros tipos de dados de código de barras.
                        }
                    }
                }
                .addOnFailureListener { excecao ->
                    Log.e("=====", excecao.printStackTrace().toString())
                }
        }
    }

    // Evento (colocar no 'onClick' do botão)
    fun capturarFoto(view: View) {
        // Chama a função 'exibirMensagemToast' para exibir um Toast
        exibirMensagemToast(this, "Clicou!!!!")
        registrarCaptura.launch(null) // Disparar o evento registrado
    }
}

// Função para exibir um Toast na tela
fun exibirMensagemToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}

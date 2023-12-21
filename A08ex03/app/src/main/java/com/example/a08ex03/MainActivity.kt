package com.example.a08ex03

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.a08ex03.api.OutroEndpoint
import com.example.a08ex03.models.OutroUser
import com.example.a08ex03.utils.OutraNetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val outroBotao: Button = findViewById(R.id.btnFind)
        val caixaDeTexto: EditText = findViewById(R.id.editText)

        outroBotao.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Lógica a ser executada quando o botão for clicado
                buscarUsuarios(caixaDeTexto.text.toString())
            }
        })
    }

    // Fiz algumas modificações nos slides
    // A modificação do projeto é: a pessoa escreve o nome de um usuário do GitHub e clica no botão para pesquisar
    fun buscarUsuarios(usuario: String) {

        // Separei em um pacote e uma classe para poder melhorar a organização do código
        val outraRetrofitClient = OutraNetworkUtils
            .getOutraRetrofitInstance("https://api.github.com/")

        // Para poder lançar a requisição com o nome de usuário digitado pela pessoa, utilizo o
        // @GET com parâmetro path, que pode ser visto na classe OutroEndpoint
        val outroEndpoint = outraRetrofitClient.create(OutroEndpoint::class.java)
        val usuarios = outroEndpoint.getUsuarios(usuario).enqueue(object : Callback<OutroUser> {
            override fun onFailure(call: Call<OutroUser>, t: Throwable) {
                Log.d("TAG_", "Houve um erro!")
                t.printStackTrace()
            }

            // Segui o mesmo esquema mostrado no slide, apenas mudando para mostrar as informações do usuário
            // ou "usuário não encontrado"
            override fun onResponse(call: Call<OutroUser>, response: Response<OutroUser>) {
                val dados = response.body()
                if (dados?.name == null) {
                    findViewById<TextView>(R.id.txt).text = "Usuário não encontrado!"
                } else {
                    var outroUsuario = "${response.body()?.name}, ${response.body()?.login}"
                    val imgFoto = findViewById<ImageView>(R.id.imageView)
                    findViewById<TextView>(R.id.txt).text = outroUsuario

                    // Utilizei a base do código do A08ex01 que fiz, apenas adaptando para a imagem
                    // Usei a mesma biblioteca, apenas colocando um tamanho fixo na imagem
                    Glide.with(applicationContext)
                        .load(response.body()?.avatar_url)
                        .apply(
                            RequestOptions()
                                .override(
                                    400,
                                    400
                                ) // Defina a largura e altura desejadas
                        ).into(imgFoto)
                }
            }
        })
    }
}

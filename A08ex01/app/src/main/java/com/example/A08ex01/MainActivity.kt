package com.example.A08ex01

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.A08ex01.R.id.textEmpresa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var txtResultado: TextView
    private lateinit var outraEmpresa: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos componentes da interface
        txtResultado = findViewById(R.id.txtResultado)
        outraEmpresa = findViewById(R.id.textEmpresa)

        // Configuração do serviço Retrofit
        val outroServico = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        // Chamada da API ao abrir o aplicativo
        outroServico.getUser().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful) {
                    val dadosRecebidos = response.body()
                    val outroNome = dadosRecebidos?.name
                    val outraCompany = dadosRecebidos?.company

                    // Atualiza o TextView com o resultado
                    txtResultado.text = "Nome: $outroNome"
                    outraEmpresa.text = "Empresa: $outraCompany"

                } else {
                    txtResultado.text = "Erro na chamada da API"
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                txtResultado.text = "Erro na chamada da API"
            }
        })

        // Chama a função 'exibirOutroToast' para exibir um Toast
        exibirOutroToast(this, "Consumindo API!!!!")
    }
}

// Função para exibir um Toast na tela
fun exibirOutroToast(context: Context, mensagem: String) {
    // Cria um Toast com a mensagem fornecida e exibe-o na tela
    Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
}

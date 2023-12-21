package com.example.A08ex02

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var outraTxtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outroService = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(OutroUserService::class.java)

        outraTxtResultado = findViewById(R.id.txtResultado)

        outroService.getOutrosUsuarios(5).enqueue(object : Callback<OutroApiResponse> {
            override fun onResponse(call: Call<OutroApiResponse>, response: Response<OutroApiResponse>) {

                if (response.isSuccessful) {
                    val outrosDadosRecebidos = response.body()?.outrosResultados
                    if (!outrosDadosRecebidos.isNullOrEmpty()) {
                        // Filtra apenas os usuários que têm nomes
                        val outrosUsuariosComNomes = outrosDadosRecebidos.filter { it.outroNome != null }

                        if (outrosUsuariosComNomes.isNotEmpty()) {
                            // Leva apenas os primeiros 5 usuários com nomes
                            val outrosUsuarios = outrosUsuariosComNomes.take(30)
                                .map { "Pessoa: <b>${it.outroNome?.nomeCompleto}</b>" }
                            outraTxtResultado.text =
                                "Outros Dados:\n${outrosUsuarios.joinToString("\n")}"

                            // Exibe o Snackbar com a mensagem "Dados carregados com sucesso"
                        } else {
                            outraTxtResultado.text = "Nenhum usuário com nome encontrado"
                        }
                    } else {
                        outraTxtResultado.text = "Erro na chamada da API: ${response.message()}"
                    }
                }
            }

            override fun onFailure(call: Call<OutroApiResponse>, t: Throwable) {
                outraTxtResultado.text = "Erro na chamada da API"
            }
        })
    }
}

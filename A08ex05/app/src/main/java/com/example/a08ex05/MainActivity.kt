package com.example.a08ex05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.a08ex05.api.OutroEndpoint
import com.example.a08ex05.models.OutroUser
import com.example.a08ex05.models.OutroUserResponse
import com.example.a08ex05.utils.OutraNetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val outroBotao: Button = findViewById(R.id.btnFind)
        val caixaDeTexto: EditText = findViewById(R.id.editText)

        // Usuário digita no campo quantos usuários random ele quer criar
        outroBotao.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                // Lógica a ser executada quando o botão for clicado
                buscarUsuarios(caixaDeTexto.text.toString().toInt())
            }
        })
    }

    fun buscarUsuarios(numero: Int) {
        // Separei em um pacote e uma classe para poder melhorar a organização do código
        val outraRetrofitClient = OutraNetworkUtils
            .getOutraRetrofitInstance("https://randomuser.me/")

        // Para poder lançar a requisição com o nome de usuário digitado pela pessoa, utilizo o
        // @GET com parâmetro query, que pode ser visto na classe OutroEndpoint
        // Foi usado o @Query porque a URL termina com /api/?api=10
        val outroEndpoint = outraRetrofitClient.create(OutroEndpoint::class.java)
        outroEndpoint.getOutrosUsuarios(numero).enqueue(object : Callback<OutroUserResponse> {
            override fun onFailure(call: Call<OutroUserResponse>, t: Throwable) {
                Log.d("TAG_", "Houve um erro!")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<OutroUserResponse>, response: Response<OutroUserResponse>) {
                // Cria uma lista de usuários
                val outrosUsers: List<OutroUser> = response.body()!!.outrosResults

                // Busca os emails de todos os usuários
                val outroAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_list_item_1,
                    outrosUsers.map { it.outroName.outroFirst + " " + it.outroName.outroLast })

                // Conectando o adapter à ListView
                val outraListView = findViewById<ListView>(R.id.listView)
                outraListView.adapter = outroAdapter
            }
        })
    }
}

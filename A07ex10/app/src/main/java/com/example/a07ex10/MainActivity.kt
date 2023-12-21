package com.example.a07ex10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import android.content.Context
import android.widget.Toast

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapaCustomizado: GoogleMap

    // Método chamado quando a atividade é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fragmento de Suporte ao Mapa notificará quando o mapa estiver pronto para uso.
        val fragmentoDoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoDoMapa.getMapAsync(this)
    }

    // Callback chamado quando o mapa está pronto para uso
    override fun onMapReady(googleMap: GoogleMap) {
        mapaCustomizado = googleMap

        // Função 'exibirMensagemToast' é chamada para mostrar um Toast personalizado
        exibirMensagemToast(this, "Mapa pronto para uso!")
    }
}

// Função para exibir um Toast personalizado na tela
fun exibirMensagemToast(contexto: Context, mensagemPersonalizada: String) {
    Toast.makeText(contexto, mensagemPersonalizada, Toast.LENGTH_SHORT).show()
}

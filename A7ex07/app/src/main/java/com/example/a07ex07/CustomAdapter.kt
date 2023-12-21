package com.example.a07ex07

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet: ArrayList<Produto>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.findViewById<TextView>(R.id.titulo)
        val valor = view.findViewById<TextView>(R.id.valor)
        val foto = view.findViewById<ImageView>(R.id.figura)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titulo.text = dataSet[position].titulo
        holder.valor.text = dataSet[position].descricao
        holder.foto.setImageResource(dataSet[position].foto)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

package com.example.teste2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.ItemServicoActivity
import com.example.teste2.databinding.ItemServicoBinding
import com.example.teste2.models.Comentario
import com.example.teste2.store.Data

class ListaComentarioAdapter (private val dataSet: Array<Comentario>)
    : RecyclerView.Adapter<ListaComentarioAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemServicoBinding) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView
        val txtAvaliacao: TextView
        lateinit var serviceId: String

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            txtAvaliacao = binding.txtAvaliacaoEstrelas
            txtTitulo.setOnClickListener(View.OnClickListener {
                Data.selectedServiceId = serviceId
                val intent = Intent(binding.root.context, ItemServicoActivity::class.java).apply {
                }
                startActivity(binding.root.context,intent,null)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServicoBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Data.db
            .collection("User")
            .document(dataSet[position].clientId)
            .get()
            .addOnSuccessListener {
                task ->
                holder.txtTitulo.text = task.get("email").toString()
            }
        holder.txtAvaliacao.text = dataSet[position].rating.toString()
        holder.txtDescricao.text = dataSet[position].description
        holder.serviceId = dataSet[position].id
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
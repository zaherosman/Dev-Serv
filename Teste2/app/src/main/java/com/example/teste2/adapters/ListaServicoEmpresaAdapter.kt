package com.example.teste2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.ProgressoServicoEmpresaActivity
import com.example.teste2.databinding.ItemServicoBinding

class ListaServicoEmpresaAdapter (private val dataSet: Array<String>)
    : RecyclerView.Adapter<ListaServicoEmpresaAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemServicoBinding) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView
        val txtAvaliacao: TextView

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            txtAvaliacao = binding.txtAvaliacaoEstrelas
            txtTitulo.setOnClickListener(View.OnClickListener {
                val intent = Intent(binding.root.context, ProgressoServicoEmpresaActivity::class.java).apply {
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
        holder.txtTitulo.text = dataSet[position]
        holder.txtDescricao.text = position.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
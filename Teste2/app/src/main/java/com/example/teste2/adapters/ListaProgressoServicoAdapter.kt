package com.example.teste2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.ProgressoServicoClienteActivity
import com.example.teste2.databinding.ItemServicoBinding
import com.example.teste2.models.ServiceStatusComplete
import com.example.teste2.store.Data

class ListaProgressoServicoAdapter (private val dataSet: MutableList<ServiceStatusComplete>)
    : RecyclerView.Adapter<ListaProgressoServicoAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemServicoBinding) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView
        val txtAvaliacao: TextView
        lateinit var item: ServiceStatusComplete

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            txtAvaliacao = binding.txtAvaliacaoEstrelas
            txtTitulo.setOnClickListener(View.OnClickListener {
                val intent = Intent(binding.root.context, ProgressoServicoClienteActivity::class.java).apply {
                }
                Data.serviceStatus = item.serviceStatus
                startActivity(binding.root.context,intent,null)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServicoBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitulo.text = dataSet[position].service.title
        holder.txtDescricao.text = dataSet[position].serviceStatus.statusNumber.toString() + "%"
        holder.item = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
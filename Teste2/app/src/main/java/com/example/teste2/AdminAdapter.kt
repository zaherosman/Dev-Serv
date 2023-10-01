package com.example.teste2

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R.layout
import com.example.teste2.databinding.CustomDialogBinding
import com.example.teste2.databinding.ItemServicoBinding

class AdminAdapter(private val dataSet: Array<String>, private val dialogLayout : Int)
    : RecyclerView.Adapter<AdminAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemServicoBinding, dialogLayout: Int) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView
        val txtAvaliacao: TextView

        lateinit var messageBoxView : View
        lateinit var messageBoxBinding : CustomDialogBinding

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            txtAvaliacao = binding.txtAvaliacaoEstrelas
            messageBoxView = LayoutInflater.from(binding.root.context).inflate(R.layout.custom_dialog, null)
            txtTitulo.setOnClickListener(View.OnClickListener {
                val inflater = LayoutInflater.from(binding.root.context)
                messageBoxView = inflater.inflate(dialogLayout, null)

                messageBoxBinding = CustomDialogBinding.inflate(inflater)

                AlertDialog.Builder(binding.root.context).setView(
                    messageBoxView
                ).show()

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServicoBinding.inflate(layoutInflater, parent, false), dialogLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitulo.text = position.toString()
        holder.txtDescricao.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
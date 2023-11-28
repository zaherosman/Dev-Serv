package com.example.teste2.adapters.admin

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R
import com.example.teste2.databinding.AdminDialogServicoBinding
import com.example.teste2.databinding.CustomDialogBinding
import com.example.teste2.databinding.ItemServicoBinding
import com.example.teste2.databinding.ItemUsuarioBinding
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class AdminServicoAdapter (private val dataSet: Array<Servico>, private val dialogLayout : Int)
    : RecyclerView.Adapter<AdminServicoAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemUsuarioBinding, dialogLayout: Int) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView

        lateinit var servico: Servico
        lateinit var messageBoxView : View
        lateinit var messageBoxBinding : AdminDialogServicoBinding

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            messageBoxView = LayoutInflater.from(binding.root.context).inflate(R.layout.admin_dialog_servico, null)
            binding.btnApagar.setOnClickListener(View.OnClickListener {
                Data.db.collection("Service")
                    .document(servico.id)
                    .delete()
            })
            txtTitulo.setOnClickListener(View.OnClickListener {
                val inflater = LayoutInflater.from(binding.root.context)

                messageBoxBinding = AdminDialogServicoBinding.inflate(inflater)

                messageBoxBinding.txtAdminServicoTitulo.setText(servico.title)
                messageBoxBinding.txtAdminServicoDescricao.setText(servico.description)
                messageBoxBinding.txtAdminServicoTipo.setText(servico.serviceType)
                messageBoxBinding.txtAdminServicoValor.setText(servico.price.toString())
                messageBoxBinding.txtAdminServicoAcessos.setText(servico.accesses.toString())
                messageBoxBinding.ratingBar.rating = servico.averageRating.toFloat()

                messageBoxBinding.btnAdminEditar.setOnClickListener(View.OnClickListener {
                    val editValues = HashMap<String, Any>()
                    editValues.set("title", messageBoxBinding.txtAdminServicoTitulo.text.toString())
                    editValues.set("description", messageBoxBinding.txtAdminServicoDescricao.text.toString())
                    editValues.set("serviceType", messageBoxBinding.txtAdminServicoTipo.text.toString())
                    editValues.set("averageRating", parseDouble(messageBoxBinding.ratingBar.rating.toString()))
                    editValues.set("price", parseDouble(messageBoxBinding.txtAdminServicoValor.text.toString()))
                    editValues.set("accesses", parseInt(messageBoxBinding.txtAdminServicoAcessos.text.toString()))
                    Data.db
                        .collection("Service")
                        .document(servico.id)
                        .update(editValues)

                })

                AlertDialog.Builder(binding.root.context).setView(
                    messageBoxBinding.root
                ).show()

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemUsuarioBinding.inflate(layoutInflater, parent, false), dialogLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.servico = dataSet[position]
        holder.txtTitulo.text = dataSet[position].title
        holder.txtDescricao.text = dataSet[position].description
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
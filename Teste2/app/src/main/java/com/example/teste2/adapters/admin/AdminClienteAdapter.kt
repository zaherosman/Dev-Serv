package com.example.teste2.adapters.admin

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R
import com.example.teste2.databinding.AdminDialogClienteBinding
import com.example.teste2.databinding.ItemUsuarioBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data
import com.google.firebase.auth.FirebaseAuth

class AdminClienteAdapter (private val dataSet: Array<UserData>, private val dialogLayout : Int)
    : RecyclerView.Adapter<AdminClienteAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemUsuarioBinding, dialogLayout: Int) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView
        lateinit var cliente: UserData

        lateinit var messageBoxView: View
        lateinit var messageBoxBinding: AdminDialogClienteBinding

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            messageBoxView = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.admin_dialog_cliente, null)
            binding.btnApagar.setOnClickListener(View.OnClickListener {
                Data.db.collection("User")
                    .document(cliente.id)
                    .delete()
            })
            txtTitulo.setOnClickListener(View.OnClickListener {
                val inflater = LayoutInflater.from(binding.root.context)
                messageBoxView = inflater.inflate(dialogLayout, null)

                messageBoxBinding = AdminDialogClienteBinding.inflate(inflater)

                messageBoxBinding.txtAdminClienteNome.setText(cliente.name)
                messageBoxBinding.txtAdminClienteSobrenome.setText(cliente.surname)
                messageBoxBinding.txtAdminNomeUsuario.setText(cliente.username)
                messageBoxBinding.txtAdminClienteIdentifier.setText(cliente.identifier)

                messageBoxBinding.btnAdminEditar.setOnClickListener(View.OnClickListener {
                    val editValues = HashMap<String, Any>()
                    editValues.set("name", messageBoxBinding.txtAdminClienteNome.text.toString())
                    editValues.set(
                        "surname",
                        messageBoxBinding.txtAdminClienteSobrenome.text.toString()
                    )
                    editValues.set(
                        "identifier",
                        messageBoxBinding.txtAdminClienteIdentifier.text.toString()
                    )
                    Data.db
                        .collection("User")
                        .document(cliente.id)
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
        holder.cliente = dataSet[position]
        holder.txtTitulo.text = dataSet[position].email
        holder.txtDescricao.text = dataSet[position].name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
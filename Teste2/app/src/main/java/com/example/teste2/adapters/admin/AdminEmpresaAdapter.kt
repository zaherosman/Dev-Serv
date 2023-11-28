package com.example.teste2.adapters.admin

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.teste2.R
import com.example.teste2.adapters.AdminAdapter
import com.example.teste2.databinding.AdminDialogEmpresaBinding
import com.example.teste2.databinding.CustomDialogBinding
import com.example.teste2.databinding.ItemServicoBinding
import com.example.teste2.databinding.ItemUsuarioBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data
import com.google.firebase.firestore.auth.User

class AdminEmpresaAdapter (private val dataSet: Array<UserData>, private val dialogLayout : Int)
    : RecyclerView.Adapter<AdminEmpresaAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemUsuarioBinding, dialogLayout: Int) : RecyclerView.ViewHolder(binding.root) {

        val txtTitulo: TextView
        val txtDescricao: TextView

        lateinit var messageBoxView : View
        lateinit var messageBoxBinding : AdminDialogEmpresaBinding
        lateinit var empresa : UserData

        init {
            txtTitulo = binding.txtTitulo
            txtDescricao = binding.txtDescricao
            messageBoxView = LayoutInflater.from(binding.root.context).inflate(R.layout.admin_dialog_empresa, null)
            binding.btnApagar.setOnClickListener(View.OnClickListener {
                Data.db.collection("User")
                    .document(empresa.id)
                    .delete()
            })
            txtTitulo.setOnClickListener(View.OnClickListener {
                val inflater = LayoutInflater.from(binding.root.context)

                messageBoxBinding = AdminDialogEmpresaBinding.inflate(inflater)

                messageBoxBinding.txtAdminEmpresaNome.setText(empresa.name)
                messageBoxBinding.txtAdminEmpresaSetor.setText(empresa.sector)
                messageBoxBinding.txtAdminEmpresaDescricao.setText(empresa.description)
                messageBoxBinding.txtAdminEmpresaNomeUsuario.setText(empresa.username)
                messageBoxBinding.txtAdminEmpresaIdentifier.setText(empresa.identifier)

                messageBoxBinding.btnAdminEditar.setOnClickListener(View.OnClickListener {
                    val editValues = HashMap<String, Any>()
                    editValues.set("name", messageBoxBinding.txtAdminEmpresaNome.text.toString())
                    editValues.set("sector", messageBoxBinding.txtAdminEmpresaSetor.text.toString())
                    editValues.set("description", messageBoxBinding.txtAdminEmpresaDescricao.text.toString())
                    editValues.set("identifier", messageBoxBinding.txtAdminEmpresaIdentifier.text.toString())
                    editValues.set("username", messageBoxBinding.txtAdminEmpresaNomeUsuario.text.toString())
                    Data.db
                        .collection("User")
                        .document(empresa.id)
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
        holder.empresa = dataSet[position]
        holder.txtTitulo.text = dataSet[position].email
        holder.txtDescricao.text = dataSet[position].sector
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}
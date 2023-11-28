package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.AdminAdapter
import com.example.teste2.adapters.admin.AdminClienteAdapter
import com.example.teste2.adapters.admin.AdminEmpresaAdapter
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.CustomDialogBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data

class AdminListaClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminListaClientesBinding

    lateinit var messageBoxView : View
    lateinit var messageBoxBinding : CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminListaClientesBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })


        buscarClientes()

        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        messageBoxView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

        val view = binding.root
        setContentView(view)
    }

    fun buscarClientes() {
        Data.db
            .collection("User")
            .whereEqualTo("role","CLIENTE")
            .get()
            .addOnSuccessListener { taskOwner ->
                binding.listaAdminServicos.adapter = AdminClienteAdapter(
                    taskOwner.documents.map { item ->
                        UserData(
                            id= item.id,
                            identifier= item.get("identifier").toString(),
                            description= item.get("description").toString(),
                            email= item.get("email").toString(),
                            username= item.get("username").toString(),
                            name= item.get("name").toString(),
                            surname= item.get("surname").toString(),
                            role= item.get("role").toString(),
                            sector= item.get("sector").toString()
                        )
                    }.toTypedArray(), R.layout.admin_dialog_cliente
                )

                binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
                    this.finish()
                })
            }
    }

    fun abrirDialogEditarClientes() {

    }
}
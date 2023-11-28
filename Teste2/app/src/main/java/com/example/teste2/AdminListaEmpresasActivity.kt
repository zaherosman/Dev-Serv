package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.AdminAdapter
import com.example.teste2.adapters.admin.AdminEmpresaAdapter
import com.example.teste2.databinding.ActivityAdminListaEmpresasBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data

class AdminListaEmpresasActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminListaEmpresasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminListaEmpresasBinding.inflate(layoutInflater)

        buscarEmpresas()

        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        buscarEmpresas()
    }

    fun buscarEmpresas() {
        Data.db
            .collection("User")
            .whereEqualTo("role","FORNECEDOR")
            .get()
            .addOnSuccessListener { taskOwner ->
                binding.listaAdminServicos.adapter = AdminEmpresaAdapter(
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
                    }.toTypedArray(), R.layout.admin_dialog_empresa
                )

                binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
                    this.finish()
                })
            }
    }
}
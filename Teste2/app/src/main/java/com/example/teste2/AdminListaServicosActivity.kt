package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.AdminAdapter
import com.example.teste2.adapters.ListaServicoAdapter
import com.example.teste2.adapters.admin.AdminServicoAdapter
import com.example.teste2.databinding.ActivityAdminListaServicosBinding
import com.example.teste2.databinding.EditarServicosDialogBinding
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double

class AdminListaServicosActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminListaServicosBinding

    lateinit var messageBoxView : View
    lateinit var messageBoxBinding : EditarServicosDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminListaServicosBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        buscarServicos()

        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        messageBoxView = LayoutInflater.from(this).inflate(R.layout.editar_servicos_dialog, null)

        messageBoxBinding = EditarServicosDialogBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        buscarServicos()
    }

    fun buscarServicos() {
        Data.db
            .collection("Service")
            .get()
            .addOnSuccessListener {  task ->
                var servicos : List<Servico> = task.documents.map { item ->
                    Servico(
                        item.id,
                        item.data?.get("title").toString(),
                        item.data?.get("description").toString(),
                        item.data?.get("ownerId").toString(),
                        item.data?.get("serviceType").toString(),
                        Double.parseDouble(item.data?.get("averageRating").toString()),
                        Double.parseDouble(item.data?.get("price").toString()),
                        Integer.parseInt(item.data?.get("accesses").toString()),
                    )
                }
                binding.listaAdminServicos.adapter = AdminServicoAdapter(
                    servicos.toTypedArray(), dialogLayout = R.layout.admin_dialog_servico
                )
                binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)
            }
    }
}
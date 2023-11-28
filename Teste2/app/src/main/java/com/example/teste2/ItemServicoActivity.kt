package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.ListaComentarioAdapter
import com.example.teste2.databinding.ActivityItemServicoBinding
import com.example.teste2.listeners.ItemSelectedListener
import com.example.teste2.models.Comentario
import com.example.teste2.models.ServiceStatus
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double.parseDouble

class ItemServicoActivity : AppCompatActivity() {

    private lateinit var itemServicoBinding: ActivityItemServicoBinding
    lateinit var service : Servico

    lateinit var serviceTypes : Array<String>

    var serviceType : Double = 1.0

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_servico)

        serviceTypes = this.resources.getStringArray(R.array.serviceTypes)

        Toast.makeText(this, "Aberto item de servico de Id " + Data.selectedServiceId, Toast.LENGTH_LONG).show()

        itemServicoBinding = ActivityItemServicoBinding.inflate(layoutInflater)

        Data.db
            .collection("Service")
            .document(Data.selectedServiceId.toString())
            .get()
            .addOnSuccessListener { task ->
                val item = task
                if(item == null) {
                    Toast.makeText(this, "Serviço selecionado não foi encontrado!", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
                }
                val data = item.data
                service = Servico(
                    id = item.id,
                    title = data?.get("title").toString(),
                    description = data?.get("description").toString(),
                    ownerId = data?.get("ownerId").toString(),
                    serviceType = data?.get("serviceType").toString(),
                    averageRating = parseDouble(data?.get("averageRating").toString()),
                    price = parseDouble(data?.get("price").toString()),
                    accesses = Integer.parseInt(data?.get("accesses").toString())
                )
                Data.db
                    .collection("User")
                    .document(service.ownerId)
                    .get()
                    .addOnSuccessListener { taskOwner ->
                        val owner = taskOwner
                        itemServicoBinding.txtContratoNomeFornecedor.text = owner?.data?.get("name").toString()
                        itemServicoBinding.txtContratoEmailFornecedor.text = owner?.data?.get("email").toString()
                        itemServicoBinding.txtContratoSetorFornecedor.text = owner?.data?.get("sector").toString()
                        itemServicoBinding.txtItemServicoDescricao.setText(service.description)
                    }
            }

        Data.db
            .collection("Comments")
            .get()
            .addOnSuccessListener { task ->
                var comentarios : List<Comentario> = task.documents.map { item ->
                    val data = item.data
                    Comentario(
                        id = item.id,
                        clientId = data?.get("clientId").toString(),
                        description = data?.get("description").toString(),
                        rating = parseDouble(data?.get("rating").toString()),
                        serviceId = data?.get("serviceId").toString()
                    )
                }
                itemServicoBinding.listaComentarios.adapter = ListaComentarioAdapter(
                    comentarios.toTypedArray()
                )

                itemServicoBinding.listaComentarios.layoutManager = LinearLayoutManager(this)

                itemServicoBinding.txtPrecoItemServico.setText("R$ " + service.price)

                itemServicoBinding.spinTipoDesenvolvimento.onItemSelectedListener = ItemSelectedListener({ idx ->
                    serviceType = parseDouble(serviceTypes[idx].split("-")[0])
                    itemServicoBinding.txtPrecoItemServico.setText("R$ " + serviceType * service.price)
                })
            }

        itemServicoBinding.btnSolicitar.setOnClickListener(View.OnClickListener {

            Data.serviceStatus = ServiceStatus(
                id = "",
                clientId = Data.userData.id,
                ownerId = service.ownerId,
                endingMonth = "",
                endingYear = "",
                price = (serviceType * service.price),
                serviceId = service.id,
                statusDescription = "Iniciado",
                statusNumber = 1.0
            )
            val intent = Intent(this, ContratoServicoActivity::class.java).apply {
            }
            startActivity(intent)
        })

        itemServicoBinding.imgRetornarPrincipal.setOnClickListener({
            this.finish()
        })

        val view = itemServicoBinding.root
        setContentView(view)
    }
}
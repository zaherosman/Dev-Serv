package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.ListaProgressoServicoAdapter
import com.example.teste2.databinding.ActivityListaServicoClienteBinding
import com.example.teste2.models.ServiceStatus
import com.example.teste2.models.ServiceStatusComplete
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class ListaServicoClienteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListaServicoClienteBinding

    var servicos = mutableListOf<ServiceStatusComplete>()
    var servicosContratados = mutableListOf<ServiceStatusComplete>()

    var valorTotalServicosFinalizados = 0.0
    var valorTotalServicosContratados = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = 0xFFF87060.toInt()

        binding = ActivityListaServicoClienteBinding.inflate(layoutInflater)


        binding.btnMenu.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.btnSair.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.btnFiltrar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, FilterActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.btnPerfil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PerfilActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.txtValorFinalizado.text = "R$ " + valorTotalServicosFinalizados
        binding.txtValorPrestado.text = "R$ " + valorTotalServicosContratados

        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        buscarServicosCompletos()
        buscarServicosContratados()
    }

    fun buscarServicosCompletos() {
        servicos.clear()
        Data.db
            .collection("ServiceStatus")
            .whereEqualTo("clientId",Data.userData.id.toString())
            .get()
            .addOnSuccessListener { task ->
                task.documents.forEach() { status ->
                    var data = status.data
                    if(parseDouble(data?.get("statusNumber").toString()) == 100.0) {

                        Data.db.collection("Service")
                            .document(data?.get("serviceId").toString()).get().addOnSuccessListener { service ->
                                var serviceData = service.data
                                var servico = Servico(
                                    id = service.id,
                                    title = serviceData?.get("title").toString(),
                                    description = serviceData?.get("description").toString(),
                                    ownerId = serviceData?.get("ownerId").toString(),
                                    serviceType = serviceData?.get("serviceType").toString(),
                                    averageRating = parseDouble(
                                        serviceData?.get("averageRating").toString()
                                    ),
                                    price = parseDouble(serviceData?.get("price").toString()),
                                    accesses = parseInt(serviceData?.get("accesses").toString())
                                )
                                if(Data.isOnFilter(servico)) {
                                    servicos.add(ServiceStatusComplete(
                                        service = servico,
                                        serviceStatus = ServiceStatus(
                                            id = status.id,
                                            clientId = data?.get("clientId").toString(),
                                            ownerId = data?.get("ownerId").toString(),
                                            endingMonth = data?.get("endingMonth").toString(),
                                            endingYear = data?.get("endingYear").toString(),
                                            price = parseDouble(data?.get("price").toString()),
                                            serviceId = data?.get("serviceId").toString(),
                                            statusDescription = data?.get("statusDescription").toString(),
                                            statusNumber = parseDouble(data?.get("statusNumber").toString())
                                        )
                                    ))
                                }
                                valorTotalServicosFinalizados += parseDouble(data?.get("price").toString())
                                binding.txtValorFinalizado.text = "R$ " + valorTotalServicosFinalizados
                                binding.listaServico.adapter = ListaProgressoServicoAdapter(
                                    servicos
                                )
                            }
                    }
                }
            }
        binding.listaServico.layoutManager = LinearLayoutManager(baseContext)
    }

    fun buscarServicosContratados() {
        servicosContratados.clear()
        Data.db
            .collection("ServiceStatus")
            .whereEqualTo("clientId",Data.userData.id.toString())
            .get()
            .addOnSuccessListener { task ->
                task.documents.forEach() { status ->
                    var data = status.data
                    if(parseDouble(data?.get("statusNumber").toString()) < 100.0) {
                        Data.db.collection("Service")
                            .document(data?.get("serviceId").toString()).get().addOnSuccessListener { service ->
                                var serviceData = service.data
                                val servico = Servico(
                                    id = service.id,
                                    title = serviceData?.get("title").toString(),
                                    description = serviceData?.get("description").toString(),
                                    ownerId = serviceData?.get("ownerId").toString(),
                                    serviceType = serviceData?.get("serviceType").toString(),
                                    averageRating = parseDouble(
                                        serviceData?.get("averageRating").toString()
                                    ),
                                    price = parseDouble(serviceData?.get("price").toString()),
                                    accesses = parseInt(serviceData?.get("accesses").toString())
                                )
                                if(Data.isOnFilter(servico)) {
                                    servicosContratados.add(ServiceStatusComplete(
                                        service = servico,
                                        serviceStatus = ServiceStatus(
                                            id = status.id,
                                            clientId = data?.get("clientId").toString(),
                                            ownerId = data?.get("ownerId").toString(),
                                            endingMonth = data?.get("endingMonth").toString(),
                                            endingYear = data?.get("endingYear").toString(),
                                            price = parseDouble(data?.get("price").toString()),
                                            serviceId = data?.get("serviceId").toString(),
                                            statusDescription = data?.get("statusDescription").toString(),
                                            statusNumber = parseDouble(data?.get("statusNumber").toString())
                                        )
                                    ))
                                }
                                valorTotalServicosContratados += parseDouble(data?.get("price").toString())
                                binding.txtValorPrestado.text = "R$ " + valorTotalServicosContratados
                                binding.listaServicoPrestado.adapter = ListaProgressoServicoAdapter(
                                    servicosContratados
                                )
                            }
                    }
                }
            }
        binding.listaServicoPrestado.layoutManager = LinearLayoutManager(baseContext)
    }
}

package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.adapters.ListaServicoAdapter
import com.example.teste2.databinding.ActivityListaServicoBinding
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class ListaServicoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListaServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = 0xFFF87060.toInt()

        binding = ActivityListaServicoBinding.inflate(layoutInflater)

        carregarServicos()

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

        binding.btnMenu.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.btnPerfil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PerfilActivity::class.java).apply {
            }
            startActivity(intent)
        })

        val view = binding.root
        setContentView(view)
    }

    fun carregarServicos() {
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
                        parseDouble(item.data?.get("averageRating").toString()),
                        parseDouble(item.data?.get("price").toString()),
                        parseInt(item.data?.get("accesses").toString()),
                    )
                }.filter { servico ->
                    (Data.filters.serviceType == null || Data.filters.serviceType.equals(servico.serviceType))
                            && (Data.filters.minPrice == null || servico.price > Data.filters.minPrice)
                            && (Data.filters.maxPrice == null || servico.price < Data.filters.maxPrice)
                            && (Data.filters.averageRating == null || servico.averageRating > Data.filters.averageRating)
                }
                binding.listaServico.adapter = ListaServicoAdapter(
                    servicos.toTypedArray()
                )
                binding.listaServico.layoutManager = LinearLayoutManager(this)
            }
    }

    override fun onResume() {
        super.onResume()
        carregarServicos()
    }
}
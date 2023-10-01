package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.teste2.databinding.ActivityListaServicoBinding
import com.example.teste2.databinding.ActivityListaServicoClienteBinding

class ListaServicoClienteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListaServicoClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = 0xFFF87060.toInt()

        binding = ActivityListaServicoClienteBinding.inflate(layoutInflater)

        binding.listaServico.adapter = ListaProgressoServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )
        binding.listaServico.layoutManager = LinearLayoutManager(this)

        binding.btnMenu.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainEmpresaActivity::class.java).apply {
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

        val view = binding.root
        setContentView(view)
    }
}
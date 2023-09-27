package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityAdminListaEmpresasBinding
import com.example.teste2.databinding.ActivityAdminListaServicosBinding

class AdminListaEmpresasActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminListaEmpresasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminListaEmpresasBinding.inflate(layoutInflater)

        binding.listaAdminServicos.adapter = ListaServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )
        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        val view = binding.root
        setContentView(view)
    }
}
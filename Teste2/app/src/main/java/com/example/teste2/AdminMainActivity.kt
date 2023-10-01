package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityAdminMainBinding
import com.example.teste2.databinding.ActivityItemServicoBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminMainBinding.inflate(layoutInflater)

        binding.txtCliente.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AdminListaClientesActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.txtEmpresa.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AdminListaEmpresasActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.txtServico.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AdminListaServicosActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        val view = binding.root
        setContentView(view)
    }
}
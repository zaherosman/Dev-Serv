package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.ActivityProgressoServicoClienteBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class ProgressoServicoClienteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProgressoServicoClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgressoServicoClienteBinding.inflate(layoutInflater)

        binding.btnAprovar.setOnClickListener(ActivityNavigationClickListener(AvaliarServicoActivity::class.java))

        binding.btnCancelar.setOnClickListener(ActivityNavigationClickListener(ListaServicoActivity::class.java))

        setContentView(binding.root)
    }
}
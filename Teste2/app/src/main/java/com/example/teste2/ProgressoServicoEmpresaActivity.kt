package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.ActivityProgressoServicoClienteBinding
import com.example.teste2.databinding.ActivityProgressoServicoEmpresaBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class ProgressoServicoEmpresaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProgressoServicoEmpresaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgressoServicoEmpresaBinding.inflate(layoutInflater)

        binding.btnEditar.setOnClickListener(ActivityNavigationClickListener(ListaServicoEmpresaActivity::class.java))

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        binding.btnCancelar.setOnClickListener(ActivityNavigationClickListener(ListaServicoEmpresaActivity::class.java))

        setContentView(binding.root)
    }
}
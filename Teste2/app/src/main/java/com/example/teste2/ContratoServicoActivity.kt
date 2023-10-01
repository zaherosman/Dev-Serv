package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityContratoServicoBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class ContratoServicoActivity : AppCompatActivity() {

    lateinit var binding : ActivityContratoServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContratoServicoBinding.inflate(layoutInflater)

        binding.btnSolicitarServico.setOnClickListener(ActivityNavigationClickListener(ProgressoServicoClienteActivity::class.java))

        setContentView(binding.root)
    }
}
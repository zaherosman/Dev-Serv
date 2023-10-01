package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.teste2.databinding.ActivityAdminListaServicosBinding
import com.example.teste2.databinding.ActivityCadastroEmpresaBinding
import com.example.teste2.databinding.ActivityCadastroServicoBinding

class CadastroServicoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroServicoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroServicoBinding.inflate(layoutInflater)

        var btnVoltar = binding.imgRetornarPrincipal
        var btnCadastro = binding.btnCadastro

        btnVoltar.setOnClickListener{
            this.finish()
        }

        btnCadastro.setOnClickListener{
            this.finish()
        }

        val view = binding.root
        setContentView(view)
    }
}
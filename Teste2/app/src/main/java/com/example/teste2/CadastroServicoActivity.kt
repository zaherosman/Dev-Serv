package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teste2.databinding.ActivityCadastroServicoBinding
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import java.lang.Double.parseDouble

class CadastroServicoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroServicoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroServicoBinding.inflate(layoutInflater)

        val edtTitulo = binding.edtTitulo
        val edtTipoServico = binding.edtTipoServico
        val edtDescricao = binding.edtDescricao
        val edtValor = binding.edtValor

        var btnVoltar = binding.imgRetornarPrincipal
        var btnCadastro = binding.btnCadastro

        btnVoltar.setOnClickListener{
            this.finish()
        }

        btnCadastro.setOnClickListener{
            Data.db
                .collection("Service")
                .add(
                    Servico(
                        "",
                        edtTitulo.text.toString(),
                        edtDescricao.text.toString(),
                        Data.userData.id,
                        edtTipoServico.text.toString(),
                        0.0,
                        parseDouble(edtValor.text.toString()),
                        0
                    )
                )
            this.finish()
        }

        val view = binding.root
        setContentView(view)
    }
}
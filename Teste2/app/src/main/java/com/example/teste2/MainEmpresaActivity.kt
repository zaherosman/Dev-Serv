package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teste2.databinding.ActivityMainBinding
import com.example.teste2.databinding.ActivityMainEmpresaBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class MainEmpresaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainEmpresaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainEmpresaBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(
            ActivityNavigationClickListener(LoginActivity::class.java)
        )

        binding.txtServicosPrestados.setOnClickListener(
            ActivityNavigationClickListener(ListaServicoEmpresaActivity::class.java))

        binding.txtCadastrarServicos.setOnClickListener(
            ActivityNavigationClickListener(CadastroServicoActivity::class.java))

        binding.txtPerfil.setOnClickListener(
            ActivityNavigationClickListener(PerfilActivity::class.java))

        setContentView(binding.root)
    }
}
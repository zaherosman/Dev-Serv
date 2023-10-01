package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.teste2.databinding.ActivityAdminListaServicosBinding
import com.example.teste2.databinding.ActivityCadastroEmpresaBinding

class CadastroEmpresaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroEmpresaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroEmpresaBinding.inflate(layoutInflater)

        var btnVoltar = binding.imgRetornarPrincipal
        var btnLogin = binding.btnLogin
        var btnCadastro = binding.btnCadastro

        btnVoltar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        btnCadastro.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        val view = binding.root
        setContentView(view)
    }
}
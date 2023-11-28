package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.teste2.databinding.ActivityMainBinding
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Bem-vindo " + Data.userData.name + "!", Toast.LENGTH_LONG).show()

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(
            ActivityNavigationClickListener(LoginActivity::class.java)
        )

        binding.txtServicos.setOnClickListener(
            ActivityNavigationClickListener(ListaServicoClienteActivity::class.java))

        binding.txtNovosServicos.setOnClickListener(
            ActivityNavigationClickListener(ListaServicoActivity::class.java))

        binding.txtPerfil.setOnClickListener(
            ActivityNavigationClickListener(PerfilActivity::class.java))

        setContentView(binding.root)
    }
}
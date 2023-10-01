package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityFilterBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class FilterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilterBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        binding.btnFiltrarServicos.setOnClickListener(
            ActivityNavigationClickListener(ListaServicoActivity::class.java)
        )

        setContentView(binding.root)
    }
}
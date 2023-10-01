package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityAvaliarServicoBinding
import com.example.teste2.utils.ActivityNavigationClickListener

class AvaliarServicoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAvaliarServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAvaliarServicoBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })
        binding.btnFinalizarServico.setOnClickListener(ActivityNavigationClickListener(ListaServicoActivity::class.java))

        setContentView(binding.root)
    }
}
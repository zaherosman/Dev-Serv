package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.teste2.databinding.ActivityListaServicoBinding

class ListaServicoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListaServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = 0xFFF87060.toInt()

        binding = ActivityListaServicoBinding.inflate(layoutInflater)

        binding.listaServico.adapter = ListaServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )
        binding.listaServico.layoutManager = LinearLayoutManager(this)

        val view = binding.root
        setContentView(view)
    }
}
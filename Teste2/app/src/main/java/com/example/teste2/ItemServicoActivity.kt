package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityContratoServicoBinding
import com.example.teste2.databinding.ActivityItemServicoBinding
import com.example.teste2.databinding.ActivityListaServicoBinding
import com.example.teste2.databinding.ItemServicoBinding

class ItemServicoActivity : AppCompatActivity() {

    private lateinit var itemServicoBinding: ActivityItemServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_servico)

        itemServicoBinding = ActivityItemServicoBinding.inflate(layoutInflater)

        itemServicoBinding.listaComentarios.adapter = ListaServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )

        itemServicoBinding.listaComentarios.layoutManager = LinearLayoutManager(this)

        itemServicoBinding.btnSolicitar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ContratoServicoActivity::class.java).apply {
            }
            startActivity(intent)
        })

        val view = itemServicoBinding.root
        setContentView(view)
    }
}
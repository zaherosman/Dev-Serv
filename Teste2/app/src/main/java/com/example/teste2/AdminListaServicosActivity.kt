package com.example.teste2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityAdminListaServicosBinding
import com.example.teste2.databinding.ActivityListaServicoBinding
import com.example.teste2.databinding.CustomDialogBinding
import com.example.teste2.databinding.EditarServicosDialogBinding

class AdminListaServicosActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminListaServicosBinding

    lateinit var messageBoxView : View
    lateinit var messageBoxBinding : EditarServicosDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminListaServicosBinding.inflate(layoutInflater)

        binding.listaAdminServicos.adapter = ListaServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )
        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        messageBoxView = LayoutInflater.from(this).inflate(R.layout.editar_servicos_dialog, null)

        messageBoxBinding = EditarServicosDialogBinding.inflate(layoutInflater)


        AlertDialog.Builder(this).setView(
            messageBoxView
        ).show()

        val view = binding.root
        setContentView(view)
    }
}
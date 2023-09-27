package com.example.teste2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.ActivityAdminListaEmpresasBinding
import com.example.teste2.databinding.CustomDialogBinding

class AdminListaClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminListaClientesBinding

    lateinit var messageBoxView : View
    lateinit var messageBoxBinding : CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminListaClientesBinding.inflate(layoutInflater)



        binding.listaAdminServicos.adapter = ListaServicoAdapter(
            arrayOf("TEST1","TEST2","TEST3")
        )
        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        messageBoxView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

        messageBoxBinding = CustomDialogBinding.inflate(layoutInflater)

        messageBoxBinding.messageBoxContent.text = "Algum Conteúdo"

        AlertDialog.Builder(this).setView(
            messageBoxView
        ).show()

        val view = binding.root
        setContentView(view)
    }

    fun abrirDialogEditarClientes() {

    }
}
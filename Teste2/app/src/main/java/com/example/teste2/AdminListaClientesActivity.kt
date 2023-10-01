package com.example.teste2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
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

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })


        binding.listaAdminServicos.adapter = AdminAdapter(
            arrayOf("TEST1","TEST2","TEST3"), R.layout.admin_dialog_cliente
        )
        binding.listaAdminServicos.layoutManager = LinearLayoutManager(this)

        messageBoxView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

        val view = binding.root
        setContentView(view)
    }

    fun abrirDialogEditarClientes() {

    }
}
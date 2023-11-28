package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityPerfilBinding
import com.example.teste2.store.Data

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)

        var edtNome = binding.edtNomePerfil
        var edtSobrenome = binding.edtSobrenomePerfil
        var edtNomeUsuario = binding.edtNomeUsuario
        var edtEmail = binding.edtEmail
        var edtIdentifier = binding.edtIdentifier

        edtNome.setText(Data.userData.name)
        edtSobrenome.setText(Data.userData.surname)
        edtNomeUsuario.setText(Data.userData.username)
        edtEmail.setText(Data.userData.email)
        edtIdentifier.setText(Data.userData.identifier)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })
        setContentView(binding.root)
    }
}
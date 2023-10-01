package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.teste2.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        var btnLogin = binding.btnLogin
        var btnCadastro = binding.btnCadastro
        var btnPrestador = binding.btnPrestador
        val txtEmail = binding.tietEmail

        btnLogin.setOnClickListener {
            if(txtEmail.text!!.toString().trim().equals("admin")) {
                val intent = Intent(this, AdminMainActivity::class.java).apply {
                }
                startActivity(intent)
            } else if(txtEmail.text!!.toString().trim().equals("empresa")) {
                val intent = Intent(this, MainEmpresaActivity::class.java).apply {
                }
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
        btnCadastro.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java).apply {
            }
            startActivity(intent)
        }
        btnPrestador.setOnClickListener{
            val intent = Intent(this, CadastroEmpresaActivity::class.java).apply {
            }
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}
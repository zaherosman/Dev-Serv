package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin = findViewById(R.id.btnLogin) as Button
        var btnCadastro = findViewById(R.id.btnCadastro) as Button

        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
        btnCadastro.setOnClickListener{
            val intent = Intent(this, CadastroActivity::class.java).apply {
            }
            startActivity(intent)
        }


    }
}
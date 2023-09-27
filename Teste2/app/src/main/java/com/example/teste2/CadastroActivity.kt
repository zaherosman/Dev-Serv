package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var btnVoltar = findViewById(R.id.imgRetornarPrincipal) as ImageButton
        var btnLogin = findViewById(R.id.btnLogin) as Button

        btnVoltar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }


    }
}
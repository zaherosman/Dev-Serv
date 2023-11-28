package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.teste2.databinding.ActivityCadastroBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        var edtNome = binding.edtNome
        var edtSobrenome = binding.edtSobrenome
        var edtNomeUsuario = binding.edtNomeUsuario
        var edtEmail = binding.edtEmail
        var edtIdentifier = binding.edtIdentifier
        var edtSenha = binding.edtSenha
        var edtConfirmaSenha = binding.edtConfirmaSenha

        binding.imgRetornarPrincipal.setOnClickListener{
            this.finish()
        }

        binding.btnCadastro.setOnClickListener {
            var nomeUsuario: String
            var email : String
            var senha : String
            var confirmaSenha : String
            nomeUsuario = edtNomeUsuario.text.toString()
            email = edtEmail.text.toString()
            senha = edtSenha.text.toString()
            confirmaSenha = edtConfirmaSenha.text.toString()
            if(email.isBlank() || !email.contains("@")) {
                Toast.makeText(this, "E-mail Inválido.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!senha.equals(confirmaSenha)) {
                Toast.makeText(this, "As senhas não conferem.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Firebase.auth.createUserWithEmailAndPassword(
                email,
                senha
            ).addOnSuccessListener { result ->
                Data
                    .db
                    .collection("User")
                    .add(
                        UserData(
                            "",
                            edtIdentifier.text.toString(),
                            "",
                            email,
                            nomeUsuario,
                            edtNome.text.toString(),
                            edtSobrenome.text.toString(),
                            "CLIENTE",
                            ""
                        )
                    )
                this.finish()
            }

        }

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }



        setContentView(binding.root)
    }
}
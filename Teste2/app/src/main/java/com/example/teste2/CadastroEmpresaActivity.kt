package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.teste2.databinding.ActivityAdminListaServicosBinding
import com.example.teste2.databinding.ActivityCadastroEmpresaBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroEmpresaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroEmpresaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroEmpresaBinding.inflate(layoutInflater)

        var edtNomeUsuario = binding.edtNomeUsuarioEmpresa
        var edtEmail = binding.edtEmailEmpresa
        var edtSenha = binding.edtSenhaEmpresa
        var edtConfirmaSenha = binding.edtConfirmaSenhaEmpresa
        var edtCnpj = binding.edtCnpjEmpresa
        var edtNome = binding.edtNomeEmpresa

        var btnVoltar = binding.imgRetornarPrincipal
        var btnLogin = binding.btnLogin
        var btnCadastro = binding.btnCadastro
        var spinSetores = binding.spinSetores

        btnVoltar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        btnCadastro.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
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
                            edtCnpj.text.toString(),
                            "",
                            email,
                            nomeUsuario,
                            edtNome.text.toString(),
                            "",
                            "FORNECEDOR",
                            spinSetores.selectedItem.toString()
                        )
                    )
                this.finish()
            }
        }

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent)
        }

        val view = binding.root
        setContentView(view)
    }
}
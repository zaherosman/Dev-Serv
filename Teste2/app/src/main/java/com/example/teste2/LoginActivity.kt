package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.teste2.databinding.ActivityLoginBinding
import com.example.teste2.models.UserData
import com.example.teste2.store.Data
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        firebaseAuth = Firebase.auth

        var btnLogin = binding.btnLogin
        var btnCadastro = binding.btnCadastro
        var btnPrestador = binding.btnPrestador
        val txtEmail = binding.tietEmail
        val txtPassword = binding.tietPass

        btnLogin.setOnClickListener {
            var email = txtEmail.text!!.toString().trim()
            var senha = txtPassword.text!!.toString().trim()
            firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java).apply {  }
                        Data.user = task.result.user
                        Data.db
                            .collection("User")
                            .whereEqualTo("email", Data.user?.email.toString()).get().addOnSuccessListener { task ->
                                var user = task.documents.toList().get(0)
                                Data.userData = UserData(
                                    user.id,
                                    user.data?.get("identifier").toString(),
                                    user.data?.get("description").toString(),
                                    user.data?.get("email").toString(),
                                    user.data?.get("username").toString(),
                                    user.data?.get("name").toString(),
                                    user.data?.get("surname").toString(),
                                    user.data?.get("role").toString(),
                                    user.data?.get("sector").toString()
                                )
                                lateinit var intent : Intent
                                if(Data.userData.role.trim().equals("ADMIN")) {
                                    intent = Intent(this, AdminMainActivity::class.java).apply { }
                                } else if(Data.userData.role.trim().equals("FORNECEDOR"))
                                    intent = Intent(this, MainEmpresaActivity::class.java).apply {  }
                                else
                                    intent = Intent(this, MainActivity::class.java).apply {  }
                                startActivity(intent)
                            }.addOnFailureListener { ex ->
                                ex.printStackTrace()
                                Toast.makeText(this, "Erro ao buscar dados do usuário", Toast.LENGTH_LONG).show()
                            }
                    } else {
                        Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_LONG).show()
                    }
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
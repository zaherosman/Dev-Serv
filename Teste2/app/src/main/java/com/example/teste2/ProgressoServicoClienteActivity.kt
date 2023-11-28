package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.ActivityProgressoServicoClienteBinding
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener

class ProgressoServicoClienteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProgressoServicoClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgressoServicoClienteBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener {
            this.finish()
        }

        if(Data.serviceStatus != null){
            Data.db
                .collection("Service")
                .document(Data.serviceStatus!!.serviceId)
                .get()
                .addOnSuccessListener { service ->
                    binding.txtTituloProgresso.setText(service.data?.get("title").toString())
                    binding.txtTipoServicoProgresso.setText(service.data?.get("serviceType").toString())
                    binding.txtDescricaoStatusServico.setText(Data.serviceStatus!!.statusDescription)
                    binding.barraProgresso.progress = Data.serviceStatus!!.statusNumber.toInt()
                }
        }


        binding.txtPrecoServicoProgresso.text = "R$ " + Data.serviceStatus?.price

        binding.btnAprovar.setOnClickListener(View.OnClickListener {
            var updateValues = HashMap<String,Any>()
            updateValues.set("statusNumber",100)
            updateValues.set("statusDescription","Aprovado")
            Data.db
                .collection("ServiceStatus")
                .document(Data.serviceStatus!!.id)
                .update(updateValues)
            val intent = Intent(this, AvaliarServicoActivity::class.java).apply {
            }
            startActivity(intent)
        })

        binding.btnCancelar.setOnClickListener(View.OnClickListener {
            var updateValues = HashMap<String,Any>()
            updateValues.set("statusNumber",100)
            updateValues.set("statusDescription","Cancelado")
            Data.db
                .collection("ServiceStatus")
                .document(Data.serviceStatus!!.id)
                .update(updateValues)
            this.finish()
        })

        setContentView(binding.root)
    }
}
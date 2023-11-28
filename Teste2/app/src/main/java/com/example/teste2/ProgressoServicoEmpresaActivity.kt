package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityAdminListaClientesBinding
import com.example.teste2.databinding.ActivityProgressoServicoClienteBinding
import com.example.teste2.databinding.ActivityProgressoServicoEmpresaBinding
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener

class ProgressoServicoEmpresaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProgressoServicoEmpresaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgressoServicoEmpresaBinding.inflate(layoutInflater)

        if(Data.serviceStatus != null){
            Data.db
                .collection("Service")
                .document(Data.serviceStatus!!.serviceId)
                .get()
                .addOnSuccessListener { service ->
                    binding.txtTituloProgressoEmpresa.setText(service.data?.get("title").toString())
                    binding.txtTipoServicoProgressoEmpresa.setText(service.data?.get("serviceType").toString())
                    binding.txtDescricaoStatusServico.setText(Data.serviceStatus!!.statusDescription)
                    binding.barraProgresso.progress = Data.serviceStatus!!.statusNumber.toInt()
                }
        }

        binding.txtPrecoProgressoEmpresa.setText("R$ " + Data.serviceStatus?.price)

        binding.btnEditar.setOnClickListener(View.OnClickListener {


            var updateValues = HashMap<String,Any>()
            updateValues.set("statusNumber",binding.barraProgresso.progress)
            updateValues.set("statusDescription", binding.txtDescricaoStatusServico.text.toString())
            Data.db
                .collection("ServiceStatus")
                .document(Data.serviceStatus!!.id)
                .update(updateValues)
            this.finish()
        })

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
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
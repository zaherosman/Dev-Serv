package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.teste2.databinding.ActivityContratoServicoBinding
import com.example.teste2.models.Servico
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class ContratoServicoActivity : AppCompatActivity() {

    lateinit var binding : ActivityContratoServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContratoServicoBinding.inflate(layoutInflater)

        Data.db
            .collection("Service")
            .document(Data.selectedServiceId.toString())
            .get()
            .addOnSuccessListener { task ->
                val item = task
                if(item == null) {
                    Toast.makeText(this, "Serviço selecionado não foi encontrado!", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
                }
                val data = item.data
                val service = Servico(
                    id = item.id,
                    title = data?.get("title").toString(),
                    description = data?.get("description").toString(),
                    ownerId = data?.get("ownerId").toString(),
                    serviceType = data?.get("serviceType").toString(),
                    averageRating = parseDouble(data?.get("averageRating").toString()),
                    price = parseDouble(data?.get("price").toString()),
                    accesses = parseInt(data?.get("accesses").toString())
                )
                Data.db
                    .collection("User")
                    .document(service.ownerId)
                    .get()
                    .addOnSuccessListener { taskOwner ->
                        val owner = taskOwner
                        binding.txtContratoNomeFornecedor.text = owner?.data?.get("name").toString()
                        binding.txtContratoEmailFornecedor.text = owner?.data?.get("email").toString()
                        binding.txtContratoSetorFornecedor.text = owner?.data?.get("sector").toString()
                        binding.txtContratoTitulo.setText(service.title)
                        binding.txtContratoDescricaoServico.setText(service.description)
                        binding.txtContratoTipoServico.setText(service.serviceType)
                        binding.txtContratoValorServico.setText("R$ " + Data.serviceStatus?.price)
                    }
            }

        binding.imgRetornarPrincipal.setOnClickListener({
            this.finish()
        })

        binding.btnSolicitarServico.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, ProgressoServicoClienteActivity::class.java).apply {  }
            if(Data.serviceStatus == null) return@OnClickListener
            Data.db
                .collection("ServiceStatus")
                .add(
                    Data.serviceStatus!!
                )
            startActivity(intent)
        })

        setContentView(binding.root)
    }
}
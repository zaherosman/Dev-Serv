package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.teste2.databinding.ActivityAvaliarServicoBinding
import com.example.teste2.models.Comentario
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener
import java.lang.Double.parseDouble

class AvaliarServicoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAvaliarServicoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAvaliarServicoBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })
        binding.btnFinalizarServico.setOnClickListener(View.OnClickListener {
            Data.db
                .collection("Comments")
                .add(
                    Comentario(
                         id= "",
                         clientId= Data.serviceStatus!!.clientId,
                         description= binding.txtComentariosServico.text.toString(),
                         rating= binding.ratingBar.rating.toDouble(),
                         serviceId= Data.serviceStatus!!.serviceId
                    )
                )
            Data.db
                .collection("Comments")
                .whereEqualTo("serviceId", Data.serviceStatus!!.serviceId)
                .get()
                .addOnSuccessListener { task ->
                    var total = 0.0
                    var count = 0
                    task.documents.forEach() { documentSnapshot ->
                        total += parseDouble(documentSnapshot.get("rating").toString())
                        count +=1
                    }
                    if(count > 0)
                        Data.db
                            .collection("Service")
                            .document(Data.serviceStatus!!.serviceId)
                            .update("averageRating",total/count)

                    val intent = Intent(this, AvaliarServicoActivity::class.java).apply {
                    }
                    startActivity(intent)
                }


        })

        setContentView(binding.root)
    }
}
package com.example.teste2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.view.get
import com.example.teste2.databinding.ActivityFilterBinding
import com.example.teste2.store.Data
import com.example.teste2.utils.ActivityNavigationClickListener
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class FilterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFilterBinding

    var tipoServicoSelecionado: String ?= null
    var averageRatingSelecionado: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilterBinding.inflate(layoutInflater)

        binding.imgRetornarPrincipal.setOnClickListener(View.OnClickListener {
            this.finish()
        })

        binding.radioGroupTipoServico.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ radioGroup, i ->
            tipoServicoSelecionado = (radioGroup.get(i) as RadioButton).text.toString()
        })

        binding.radioAverageRating.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            averageRatingSelecionado = (radioGroup.get(i) as RadioButton).text.toString()
        })

        binding.barraPrecoMaximo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.txtFinanceiroPrecoMaximo.text = (binding.barraPrecoMaximo.progress/100.0 * 100000.0).toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        binding.barraPrecoMinimo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.txtFinanceiroPrecoMinimo.text = (binding.barraPrecoMinimo.progress/100.0 * 100000.0).toString()
                // here, you react to the value being set in seekBar
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        binding.btnFiltrarServicos.setOnClickListener(
            View.OnClickListener {
                Data.filters.maxPrice = (binding.barraPrecoMaximo.progress/100.0 * 100000.0)

                Data.filters.minPrice = (binding.barraPrecoMinimo.progress/100.0 * 100000.0)

                if(averageRatingSelecionado != null)
                    Data.filters.averageRating = parseDouble(averageRatingSelecionado)
                if(tipoServicoSelecionado != null)
                    Data.filters.serviceType = tipoServicoSelecionado
                this.finish()
            }
        )

        setContentView(binding.root)
    }
}
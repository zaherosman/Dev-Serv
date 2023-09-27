package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teste2.databinding.ActivityAdminMainBinding
import com.example.teste2.databinding.ActivityItemServicoBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
    }
}
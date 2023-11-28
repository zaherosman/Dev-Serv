package com.example.teste2.models

data class Servico(
    val id: String,
    val title: String,
    val description : String,
    val ownerId: String,
    val serviceType: String,
    val averageRating: Double,
    val price: Double,
    val accesses: Int
)
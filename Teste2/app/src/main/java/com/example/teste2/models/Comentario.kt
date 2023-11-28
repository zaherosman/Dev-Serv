package com.example.teste2.models

data class Comentario (
    val id: String,
    val clientId: String,
    val description: String,
    val rating: Double,
    val serviceId: String
)
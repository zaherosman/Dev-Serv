package com.example.teste2.models

data class ServiceStatus(
    val id: String,
    val clientId: String,
    val ownerId: String,
    val endingMonth: String,
    val endingYear: String,
    val price: Double,
    val serviceId: String,
    val statusDescription: String,
    val statusNumber: Double
)
package com.example.teste2.models

data class Filters(
    var maxPrice: Double = 9999999.9,
    var minPrice: Double = 0.0,
    var serviceType: String ?=null,
    var averageRating: Double = 0.0
)

package com.example.teste2.store

import com.example.teste2.models.Filters
import com.example.teste2.models.ServiceStatus
import com.example.teste2.models.Servico
import com.example.teste2.models.UserData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class Data {
    companion object {
        var user = Firebase.auth.currentUser
        lateinit var userData : UserData
        var db = Firebase.firestore
        var serviceStatus : ServiceStatus ?= null
        var selectedServiceId : String ?= null
        var filters :  Filters = Filters()
        fun isOnFilter(data : Servico) : Boolean {
            return data.price > filters.minPrice && data.price < filters.maxPrice && data.averageRating >= filters.averageRating && (filters.serviceType == null || data.serviceType.equals(filters.serviceType))
        }
    }

}
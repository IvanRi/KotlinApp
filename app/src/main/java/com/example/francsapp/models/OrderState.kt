package com.example.francsapp.models

enum class OrderState(var value: String) {
    IN_PREPARATION("En preparación"), IN_ROUTE("En camino"), DELIVERED("Entregado")
}
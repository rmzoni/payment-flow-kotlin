package com.manzoni.payment.entity

data class OrderItem(
        val product: Product,
        val quantity: Int = 0,
        val price: Float = 0.0f
)
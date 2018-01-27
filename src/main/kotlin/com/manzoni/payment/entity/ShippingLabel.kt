package com.manzoni.payment.entity

data class ShippingLabel(
        val item: OrderItem,
        val description: String
)
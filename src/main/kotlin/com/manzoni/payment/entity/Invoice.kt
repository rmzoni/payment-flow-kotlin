package com.manzoni.payment.entity

data class Invoice(
        val billingAddress: Address = Address(),
        val shippingAddress: Address = Address()
)
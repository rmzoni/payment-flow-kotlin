package com.manzoni.payment.entity

data class Product(
        // use type to distinguish each kind of product: physical, book, digital, membership, etc.
        val name: String = "",
        val type: String = "",
        val price: Float = 0.0f
)
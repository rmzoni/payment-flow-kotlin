package com.manzoni.payment.entity

interface Product {
    // use type to distinguish each kind of product: physical, book, digital, membership, etc.
    val name: String
    val price: Float

    fun pay(payment: Payment, item: OrderItem): Payment
}

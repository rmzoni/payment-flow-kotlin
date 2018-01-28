package com.manzoni.payment.entity

interface Product {

    val name: String
    val price: Float

    fun pay(payment: Payment, item: OrderItem): Payment
}

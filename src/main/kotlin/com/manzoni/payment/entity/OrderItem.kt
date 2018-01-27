package com.manzoni.payment.entity

data class OrderItem(
        val product: Product,
        val quantity: Int = 0,
        val price: Float = 0.0f
) {

    fun pay(payment: Payment): Payment {
        return product.pay(payment = payment, item = this)
    }
}
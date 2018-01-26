package com.manzoni.payment.entity

import java.time.LocalDateTime
import java.util.*

data class Payment(
        val authorizationNumber: Int = 0,
        val amount: Float = 0.0f,
        val invoice: Invoice = Invoice(),
        val order: Order,
        val paidAt: LocalDateTime? = null
){

    fun pay(paidAt: LocalDateTime = LocalDateTime.now()): Payment {
        return Payment(
                authorizationNumber = Random().nextInt(),
                amount = order.totalPrice(),
                invoice = Invoice(
                        billingAddress = order.address,
                        shippingAddress = order.address
                ),
                order = order.close(),
                paidAt = paidAt
        )
    }

    fun isPaid():Boolean {
        return paidAt != null
    }
}
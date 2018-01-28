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
        val payment = this.copy(
                authorizationNumber = Random().nextInt(),
                amount = order.totalPrice(),
                invoice = Invoice(
                        billingAddress = order.address,
                        shippingAddress = order.address,
                        email = order.customer.email
                )
        )

        // Pay all the items
        val paymentPayed = order.items.fold(payment, {
            currentPayment, item -> item.pay(currentPayment)
        })

        // Finalize the order / payment
        // Print the shipping label for physical products & books
        // Send email to customer confirm digital products
        return paymentPayed.copy(
             order = paymentPayed.order
                        .printShippingLabel()
                        .sendDigitalPurchaseConfirmation()
                        .sendMenbershipPurchaseConfirmation()
                        .close(),
             paidAt = paidAt
        )
    }

    fun isPaid():Boolean {
        return paidAt != null
    }
}
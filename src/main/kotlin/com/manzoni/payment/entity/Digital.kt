package com.manzoni.payment.entity

class Digital(
        override val name: String,
        override val price: Float
): Product {
    override fun pay(payment: Payment, item: OrderItem): Payment {

        // Add voucher and product to customer
        val customer = payment.order.customer.addVoucher(
                Voucher(value = 10.0f, authorizationNumber = payment.authorizationNumber)
        ).addDigitalProduct(this)


        // Refer customer to the payment
        return payment.copy(
                order = payment.order.copy(
                        customer = customer
                )
        )
    }
}
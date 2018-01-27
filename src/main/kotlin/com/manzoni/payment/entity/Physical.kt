package com.manzoni.payment.entity

class Physical(
        override val name: String,
        override val price: Float
): Product {
    override fun pay(payment: Payment, item: OrderItem): Payment {
        return payment.copy(
                order = payment.order.addItemToShippingLabel(
                        item = item,
                        description = "Full Tax"
                )
        )
    }
}
package com.manzoni.payment.entity

class Book(
        override val name: String,
        override val price: Float
): Product {
    override fun pay(payment: Payment, item: OrderItem): Payment {
        return payment.copy(
                order = payment.order.addItemToShippingLabel(
                            item = item,
                            description = "No Tax - Art. 150, VI, d"
                        )
        )
    }
}
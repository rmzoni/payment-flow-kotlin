package com.manzoni.payment.entity

class Menbership(
        override val name: String,
        override val price: Float,
        val active: Boolean = false
): Product {

    fun activate():Menbership {
        return Menbership(
                name = name,
                price = price,
                active = true
        )
    }

    override fun pay(payment: Payment, item: OrderItem): Payment {

        // Activate menbership and add to the customer
        val customer = payment.order.customer.addMenbership(
                this.activate()
        )

        // Refer customer to the payment
        return payment.copy(
                order = payment.order.copy(
                        customer = customer
                )
        )
    }
}
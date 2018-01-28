package com.manzoni.payment.entity

import java.time.LocalDateTime

data class Order(
        val customer: Customer,
        val items: List<OrderItem> = listOf(),
        val shippingLabel: List<ShippingLabel> = listOf(),
        val address: Address,
        val closedAt: LocalDateTime? = null
){
    fun addProduct(product: Product, quantity: Int = 1): Order {
        return this.copy(
                items = items.plus(OrderItem(quantity = quantity, product = product, price = product.price))
        )
    }

    fun addItemToShippingLabel(item: OrderItem, description: String): Order {
        return this.copy(
                shippingLabel = shippingLabel.plus(ShippingLabel(item = item, description = description))
        )
    }

    fun printShippingLabel(): Order{
        // TODO - Print shipping label if it is not empty
        if(!shippingLabel.isEmpty()){
            print(shippingLabel)
        }
        return this
    }

    fun sendDigitalPurchaseConfirmation():Order {
        val digitalProducts = items.filter {
            i -> i.product is Digital
        }
        if(!digitalProducts.isEmpty()){
            // TODO Send Confirmation email for digital products
        }

        return this
    }

    fun sendMenbershipPurchaseConfirmation():Order {
        items.filter {
            i -> i.product is Menbership
        }.forEach {
            i ->
            // TODO - Send Menbership Confirmation for each item

        }

        return this
    }

    fun totalPrice():Float {
        return items.map { a -> a.quantity * a.price }.sum()
    }

    fun close(closedAt: LocalDateTime = LocalDateTime.now()): Order {
        return this.copy(
                closedAt = closedAt
        )
    }

    fun isClosed(): Boolean {
        return closedAt != null
    }
}
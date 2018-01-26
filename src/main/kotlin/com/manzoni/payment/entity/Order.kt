package com.manzoni.payment.entity

import java.time.LocalDateTime

data class Order(
        val customer: Customer,
        val items: List<OrderItem> = listOf(),
        val address: Address,
        val closedAt: LocalDateTime? = null
){
    fun addProduct(product: Product, quantity: Int = 1): Order {
        return Order(
                customer = customer,
                items = items.plus(OrderItem(quantity = quantity, product = product, price = product.price)),
                address = address,
                closedAt = closedAt
        )
    }

    fun totalPrice():Float {
        return items.map { a -> a.quantity * a.price }.sum()
    }

    fun close(closedAt: LocalDateTime = LocalDateTime.now()): Order {
        return Order(
                customer = customer,
                items = items,
                address = address,
                closedAt = closedAt
        )
    }
}
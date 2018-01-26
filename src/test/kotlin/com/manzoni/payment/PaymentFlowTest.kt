package com.manzoni.payment
import com.manzoni.payment.entity.*
import kotlin.test.assertEquals
import org.junit.Test as test

class PaymentFlowTest {

    @test fun paymentGeneralTest(){

        val customer = Customer(username = "rmzoni")
        val book = Product(name = "Awesome Product", type = "book", price = 1.0f)
        val address = Address(zipcode = "01765010")
        val order = Order(customer = customer, address = address)

        // Add the book and create payment
        val payment = Payment(order = order.addProduct(book))

        // Check Payment
        val paymentPaided = payment.pay()
        assertEquals(paymentPaided.isPaid(), true, "Payment must be payed!")
        assertEquals(paymentPaided.order.items[0].product.name, "Awesome Product", "Check Product Name!")
        assertEquals(paymentPaided.invoice.billingAddress.zipcode, "01765010", "Check Billing Address!")
        assertEquals(paymentPaided.invoice.shippingAddress.zipcode, "01765010", "Check Shipping Address!")
        assertEquals(paymentPaided.amount, 1.0f, "Check Amount!")
    }

}
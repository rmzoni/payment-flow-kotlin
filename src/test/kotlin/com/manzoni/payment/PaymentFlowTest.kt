package com.manzoni.payment
import com.manzoni.payment.entity.*
import kotlin.test.assertEquals
import org.junit.Test as test

class PaymentFlowTest {

    @test fun paymentGeneralTest(){

        val customer = Customer(username = "rmzoni", email = "test@test.com")
        val book = Book(name = "Awesome Product", price = 1.0f)
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
        assertEquals(paymentPaided.invoice.email, "test@test.com", "Check Email!")
        assertEquals(paymentPaided.amount, 1.0f, "Check Amount!")
        assertEquals(paymentPaided.order.isClosed(), true, "Order Closed!")
    }

    @test fun paymentShippingLabelTest(){

        val customer = Customer(username = "rmzoni")
        val book = Book(name = "Awesome Product", price = 1.0f)
        val physicalBook = Physical(name = "Awesome Physical Product", price = 1.0f)
        val address = Address(zipcode = "01765010")
        val order = Order(customer = customer, address = address)

        // Add the book and create payment
        val payment = Payment(order = order.addProduct(book).addProduct(physicalBook))

        // Check Payment
        val paymentPaided = payment.pay()
        assertEquals(paymentPaided.isPaid(), true, "Payment must be payed!")
        assertEquals(paymentPaided.amount, 2.0f, "Check Amount!")
        assertEquals(paymentPaided.order.shippingLabel.size, 2, "Shipping Label Size!")
    }

    @test fun paymentDigitalProductTest(){

        val customer = Customer(username = "rmzoni", email = "test@test.com")
        val album = Digital(name = "Awesome Album", price = 2.0f)
        val ebook = Digital(name = "Awesome EBook", price = 3.0f)
        val address = Address(zipcode = "01765010")
        val order = Order(customer = customer, address = address)

        // Add the album and the ebook
        val payment = Payment(order = order.addProduct(album).addProduct(ebook))

        // Check Payment
        val paymentPaided = payment.pay()
        assertEquals(paymentPaided.isPaid(), true, "Payment must be payed!")
        assertEquals(paymentPaided.amount, 5.0f, "Check Amount!")
        assertEquals(paymentPaided.order.shippingLabel.size, 0, "Shipping Label Size!")
        assertEquals(paymentPaided.order.customer.vouchers.size, 2, "Customer Voucher Size!")
        assertEquals(paymentPaided.order.customer.digitalProducts.size, 2, "Customer Digital Products Size!")
    }

    @test fun paymentMenbershipProductTest(){

        val customer = Customer(username = "rmzoni", email = "test@test.com")
        val audioBooksMenbership = Menbership(name = "Awesome Album", price = 2.0f)
        val ebooksMenbership = Menbership(name = "Awesome EBook", price = 3.0f)
        val address = Address(zipcode = "01765010")
        val order = Order(customer = customer, address = address)

        // Add menberships to the order
        val payment = Payment(order = order.addProduct(audioBooksMenbership).addProduct(ebooksMenbership))

        // Check Payment
        val paymentPaided = payment.pay()
        assertEquals(paymentPaided.isPaid(), true, "Payment must be payed!")
        assertEquals(paymentPaided.amount, 5.0f, "Check Amount!")
        assertEquals(paymentPaided.order.shippingLabel.size, 0, "Shipping Label Size!")
        assertEquals(paymentPaided.order.customer.vouchers.size, 0, "Customer Voucher Size!")
        assertEquals(paymentPaided.order.customer.digitalProducts.size, 0, "Customer Digital Products Size!")
        assertEquals(paymentPaided.order.customer.menberships.size, 2, "Customer Menberships Size!")
    }

}
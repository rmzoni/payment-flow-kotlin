package com.manzoni.payment.entity

data class CreditCard (
        val id: String = ""
): PaymentMethod {
    companion object {
        fun fetchByHashed(code: Int) = CreditCard(id = "")
    }
}



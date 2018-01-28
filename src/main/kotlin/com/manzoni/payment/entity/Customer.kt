package com.manzoni.payment.entity

data class Customer(
        val username: String = "",
        val email: String = "",
        val vouchers: List<Voucher> = listOf(),
        val digitalProducts: List<Digital> = listOf(),
        val menberships: List<Menbership> = listOf()
){
    fun addVoucher(voucher: Voucher):Customer {
        return this.copy(
                vouchers = vouchers.plus(voucher)
        )
    }

    fun addDigitalProduct(digitalProduct: Digital):Customer {
        return this.copy(
                digitalProducts = digitalProducts.plus(digitalProduct)
        )
    }

    fun addMenbership(menbership: Menbership):Customer {
        return this.copy(
                menberships = menberships.plus(menbership)
        )
    }
}
package com.manzoni.payment.entity

import java.time.LocalDateTime

data class Voucher(
        val value: Float,
        val authorizationNumber: Int,
        val usedAt: LocalDateTime? = null
){
    fun use(usedAt: LocalDateTime = LocalDateTime.now()):Voucher {
        return this.copy(
                usedAt = usedAt
        )
    }

    fun isUsed():Boolean {
        return usedAt != null
    }
}
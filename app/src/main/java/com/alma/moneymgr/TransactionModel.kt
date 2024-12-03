package com.alma.moneymgr

import java.time.LocalDate

data class Transaction(
    val id: Int? = null, // Auto-generated ID
    val amount: Double,
    val type: TransactionType, // Enum: EARN or SPEND
    val category: String,
    val date: LocalDate,
    val description: String? = null
)

enum class TransactionType {
    EARN, SPEND
}
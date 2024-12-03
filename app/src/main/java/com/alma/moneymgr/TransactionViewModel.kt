package com.alma.moneymgr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    val transactions: Flow<List<Transaction>> = repository.getAllTransactions()

    fun addTransaction(
        amount: Double,
        type: TransactionType,
        category: String,
        date: LocalDate,
        description: String?
    ) {
        viewModelScope.launch {
            repository.insertTransaction(
                Transaction(
                    amount = amount,
                    type = type,
                    category = category,
                    date = date,
                    description = description
                )
            )
        }
    }
}
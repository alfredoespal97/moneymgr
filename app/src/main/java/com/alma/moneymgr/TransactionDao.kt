package com.alma.moneymgr

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE category = :category")
    fun getTransactionsByCategory(category: String): Flow<List<Transaction>>

    // Add more queries for filtering and sorting as needed
}
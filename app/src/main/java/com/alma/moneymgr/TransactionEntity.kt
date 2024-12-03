package com.alma.moneymgr

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.Flow

@androidx.room.Entity(tableName = "transactions")
data class TransactionEntity(
    @androidx.room.PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val type: String, // Store enum as String
    val category: String,
    val date: Long, // Store date as timestamp
    val description: String?
)

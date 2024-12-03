package com.alma.moneymgr

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn {
        items(transactions) { transaction ->
            TransactionItem(transaction)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Amount: ${transaction.amount}", fontSize = 18.sp)
            Text(text = "Type: ${transaction.type}", fontSize = 16.sp)
            Text(text = "Category: ${transaction.category}", fontSize = 16.sp)
            Text(text = "Date: ${transaction.date}", fontSize = 16.sp)
            if (transaction.description != null) {
                Text(text = "Description: ${transaction.description}", fontSize = 16.sp)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTransactionScreen(viewModel: TransactionViewModel) {
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(TransactionType.EARN) }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )
        // ... other input fields for type, category, date, description

        Button(onClick = {
            val transactionAmount = amount.toDoubleOrNull() ?: 0.0
            viewModel.addTransaction(
                transactionAmount,
                type,
                category,
                date,
                description
            )
            // Clear input fields or navigate back
        }) {
            Text("Add Transaction")
        }
    }
}

@Composable
fun FinanceTrackerApp(viewModel: TransactionViewModel) {
    val transactions by viewModel.transactions.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Navigate to AddTransactionScreen */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        TransactionList(transactions = transactions)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //FinanceTrackerApp()
}
package com.example.a212029_alyadamia_drnazatul_lab4

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPeriodScreen(
    onBack: () -> Unit,
    viewModel: PeriodViewModel
) {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("Not selected") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log Period") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "When did your period start?", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { showDatePicker = true }) {
                Text(text = if (selectedDate == "Not selected") "Select Date" else selectedDate)
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            val date = datePickerState.selectedDateMillis?.let {
                                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(it))
                            } ?: "Not selected"
                            selectedDate = date
                            viewModel.updatePeriodDates(start = date, end = "")//panggil viewmodel
                            showDatePicker = false
                        }) { Text("OK") }
                    }
                ) { DatePicker(state = datePickerState) }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth(), enabled = selectedDate != "Not selected") {
                Text("Save and Return")
            }
        }
    }
}
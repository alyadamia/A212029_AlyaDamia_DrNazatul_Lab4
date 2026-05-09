package com.example.a212029_alyadamia_drnazatul_Project1.data

data class PeriodUiState(
    val startDate: String = "",
    val endDate: String = "",
    val feelings: List<String> = emptyList(),
    val selectedSymptoms: List<String> = emptyList()
)
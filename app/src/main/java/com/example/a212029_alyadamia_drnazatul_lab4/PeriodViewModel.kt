package com.example.a212029_alyadamia_drnazatul_lab4

import androidx.lifecycle.ViewModel
import com.example.a212029_alyadamia_drnazatul_lab4.data.PeriodUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PeriodViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PeriodUiState())
    val uiState: StateFlow<PeriodUiState> = _uiState.asStateFlow()

    fun updatePeriodDates(start: String, end: String) {
        _uiState.update { it.copy(startDate = start, endDate = end) }
    }
    fun updateFeeling(newFeeling: String) {
        _uiState.update { it.copy(feeling = newFeeling) }
    }
}
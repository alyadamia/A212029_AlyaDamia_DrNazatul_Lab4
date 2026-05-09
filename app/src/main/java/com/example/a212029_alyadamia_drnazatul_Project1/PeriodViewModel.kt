package com.example.a212029_alyadamia_drnazatul_Project1

import androidx.lifecycle.ViewModel
import com.example.a212029_alyadamia_drnazatul_Project1.data.PeriodUiState
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
    
    fun addFeeling(newFeeling: String) {
        _uiState.update { currentState ->
            val updatedFeelings = currentState.feelings + newFeeling
            currentState.copy(feelings = updatedFeelings)
        }
    }

    fun removeFeeling(feeling: String) {
        _uiState.update { currentState ->
            val updatedFeelings = currentState.feelings - feeling
            currentState.copy(feelings = updatedFeelings)
        }
    }

    fun updateSymptoms(symptom: String) {
        _uiState.update { currentState ->
            val currentSymptoms = currentState.selectedSymptoms
            val updatedSymptoms = if (currentSymptoms.contains(symptom)) {
                currentSymptoms - symptom 
            } else {
                currentSymptoms + symptom
            }
            currentState.copy(selectedSymptoms = updatedSymptoms)
        }
    }
}
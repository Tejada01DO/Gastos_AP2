package com.ucne.gastos.ui.suplidores

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gastos.data.repository.SuplidorRepository
import com.ucne.gastos.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class SuplidoresViewModel @Inject constructor(
    private val suplidorRepository: SuplidorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SuplidoresListState())
    val uistate: StateFlow<SuplidoresListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            suplidorRepository.getSuplidores().collect(){ result ->
                when(result){
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                suplidores = result.data ?: emptyList(),
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Error",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.ucne.gastos.ui.gastos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gastos.data.remote.dto.GastosDto
import com.ucne.gastos.data.repository.GastoRepository
import com.ucne.gastos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GastosViewModel @Inject constructor(
    private val gastosRepository: GastoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GastosListState())
    val state = _state.asStateFlow()

    fun postGastos(gastosDto: GastosDto) {
        viewModelScope.launch {
            gastosRepository.postGastos(gastosDto).collectLatest  { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(gasto = GastosDto(
                            0,
                            "",
                            0,
                            "",
                            "",
                            "",
                            0,
                            0,
                            0
                        ),
                            isLoading = false,
                            error = result.message
                        )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                gasto =   GastosDto(
                                    0,
                                    "",
                                    0,
                                    "",
                                    "",
                                    "",
                                    0,
                                    0,
                                    0
                                ),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}
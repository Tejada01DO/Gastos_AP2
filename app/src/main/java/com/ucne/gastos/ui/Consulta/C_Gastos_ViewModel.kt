package com.ucne.gastos.ui.Consulta

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gastos.data.remote.dto.GastosDto
import com.ucne.gastos.data.repository.GastoRepository
import com.ucne.gastos.ui.gastos.GastosListState
import com.ucne.gastos.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class C_Gastos_ViewModel @Inject constructor(
    private val gastosRepository: GastoRepository

) : ViewModel() {

    private val _state = MutableStateFlow(GastosListState())
    val state = _state.asStateFlow()

    init {
        getGastos()
    }

    private fun getGastos() {
        viewModelScope.launch {
            gastosRepository.getGastos().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                gastos = result.data,
                                isLoading = false)
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                gastos = emptyList(),
                                isLoading = false
                            )
                        }
                    }

                }
            }
        }
    }
}

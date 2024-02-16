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

    private val _state = MutableStateFlow(GastoState())
    val state = _state.asStateFlow()

    fun postGastos() {
        viewModelScope.launch {
            gastosRepository.postGastos(_state.value.gasto).collectLatest { result ->
                when(result){
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                successMessage = "Guardado correctamente",
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = "Ocurrio un error, intentelo de nuevo",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: GastosEvent){
        when(event){
            is GastosEvent.FechaChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(fecha = event.fecha)
                    )
                }
            }

            is GastosEvent.IdSuplidorChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(idSuplidor = event.idSuplidor.toIntOrNull()?:0)
                    )
                }
            }

            is GastosEvent.NcfChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(ncf = event.ncf)
                    )
                }
            }

            is GastosEvent.ConceptoChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(concepto = event.concepto)
                    )
                }
            }

            is GastosEvent.DescuentoChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(descuento = event.descuento.toIntOrNull()?:0)
                    )
                }
            }

            is GastosEvent.ItbisChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(itbis = event.itbis.toIntOrNull()?:0)
                    )
                }
            }

            is GastosEvent.MontoChanged -> {
                _state.update{
                    it.copy(
                        gasto = it.gasto.copy(monto = event.monto.toIntOrNull()?:0)
                    )
                }
            }

            GastosEvent.onSave -> {
                postGastos()
            }

            GastosEvent.onLimpiar -> {
                _state.update{
                    it.copy(
                        gasto = GastosDto()
                    )
                }
            }

        }
    }
}

data class GastoState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val gasto: GastosDto = GastosDto()
)

sealed class GastosEvent{
    data class FechaChanged(val fecha: String): GastosEvent()
    data class IdSuplidorChanged(val idSuplidor: String): GastosEvent()
    data class NcfChanged(val ncf: String): GastosEvent()
    data class ConceptoChanged(val concepto: String): GastosEvent()
    data class DescuentoChanged(val descuento: String): GastosEvent()
    data class ItbisChanged(val itbis: String): GastosEvent()
    data class MontoChanged(val monto: String): GastosEvent()
    object onSave: GastosEvent()
    object onLimpiar: GastosEvent()
}
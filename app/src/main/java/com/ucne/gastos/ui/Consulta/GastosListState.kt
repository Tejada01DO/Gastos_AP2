package com.ucne.gastos.ui.Consulta

import com.ucne.gastos.data.remote.dto.GastosDto

data class GastosListState(
    val isLoading: Boolean = false,
    val gastos: List<GastosDto>? = emptyList(),
    val error: String? = null,
    val gasto: GastosDto? = GastosDto(
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
    val selectedGastos: GastosDto? = null
)
package com.ucne.gastos.ui.suplidores

import com.ucne.gastos.data.remote.dto.SuplidorDto

data class SuplidoresListState (
    val isLoading: Boolean = false,
    val suplidores: List<SuplidorDto> = emptyList(),
    val error: String? = null
)
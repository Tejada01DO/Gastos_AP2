package com.ucne.gastos.data.remote.dto

data class GastosDto (
    val idGasto: Int? = null,
    val fecha: String = "",
    val idSuplidor: Int? = 0,
    val suplidor: String = "",
    val ncf: String = "",
    val concepto: String = "",
    val descuento: Int? = 0,
    val itbis: Int? = 0,
    val monto: Int? = 0,
    val tipo : Int? = null,
    val numero: String = ""
)

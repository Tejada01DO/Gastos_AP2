package com.ucne.gastos.data.remote.dto

data class GastosDto (
    val idGasto: Int? = null,
    val fecha: String = "",
    val idSuplidor: Int? = null,
    val suplidor: String = "",
    val ncf: String = "",
    val concepto: String = "",
    val descuento: Int? = null,
    val itbis: Int? = null,
    val monto: Int? = null,
    val tipo : Int? = null,
    val numero: String = ""
)

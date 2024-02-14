package com.ucne.gastos.data.remote.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "Gastos")
data class GastosDto (
    @PrimaryKey
    @Json(name = "idGasto")
    val idGasto: Int? = null,
    @Json(name = "fecha")
    var fecha: String = "",
    @Json(name = "idSuplidor")
    var idSuplidor: Int? = null,
    @Json(name = "suplidor")
    var suplidor: String = "",
    @Json(name = "ncf")
    var ncf: String = "",
    @Json(name = "concepto")
    var concepto: String = "",
    @Json(name = "descuento")
    var descuento: Int? = null,
    @Json(name = "itbis")
    var itbis: Int? = null,
    @Json(name = "monto")
    var monto: Int? = null,
    @Json(name = "tipo")
    var tipo: Int? = 0,
    @Json(name = "numero")
    var numero: String = ""
)
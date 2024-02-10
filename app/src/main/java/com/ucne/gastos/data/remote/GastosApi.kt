package com.ucne.gastos.data.remote

import com.ucne.gastos.data.remote.dto.GastosDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GastosApi {
    @GET("api/gastos")
    @Headers("X-API-Key: test")
    suspend fun getGastos(@Query("idGasto") idGasto: Int?): List<GastosDto>

    @GET("api/gastos/{id}")
    @Headers("X-API-Key: test")
    suspend fun getGastoById(id: Int): GastosDto

    @POST("api/gastos")
    @Headers("X-API-Key: test")
    suspend fun addGasto(@Body gastos: GastosDto): Response<GastosDto>
}
package com.ucne.gastos.data.remote

import com.ucne.gastos.data.remote.dto.GastosDto
import com.ucne.gastos.data.remote.dto.SuplidorDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Headers

interface GastosApi {
    @GET("api/Gastos")
    @Headers("X-API-Key: test")
    suspend fun getGastos():List<GastosDto>

    @GET("api/Gastos/{id}")
    @Headers("X-API-Key: test")
    suspend fun getGastoById(id: Int): GastosDto

    @POST("api/Gastos")
    @Headers("X-API-Key: test")
    suspend fun addGasto(@Body gastos: GastosDto): GastosDto

    @GET("api/SuplidoresGastos")
    @Headers("X-API-Key: test")
    suspend fun getSuplidores() : List<SuplidorDto>
}
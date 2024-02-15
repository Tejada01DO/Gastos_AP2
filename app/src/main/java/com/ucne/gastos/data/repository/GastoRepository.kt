package com.ucne.gastos.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.ucne.gastos.data.remote.GastosApi
import com.ucne.gastos.data.remote.dto.GastosDto
import com.ucne.gastos.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GastoRepository @Inject constructor(
    private val gastosApi: GastosApi,
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getGastos(): Flow<Resource<List<GastosDto>>> = flow {
        try {
            emit(Resource.Loading())

            val gastos = gastosApi.getGastos()

            emit(Resource.Success(gastos))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    fun getGastoById(gastoId: Int): Flow<Resource<GastosDto>> = flow {
        try {
            emit(Resource.Loading())

            val gastos = gastosApi.getGastoById(gastoId)

            emit(Resource.Success(gastos))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }

    fun postGastos(gastosDto: GastosDto): Flow<Resource<GastosDto>> = flow{
        try {
            emit(Resource.Loading())

            val response = gastosApi.addGasto(gastosDto)

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}

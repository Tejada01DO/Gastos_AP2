package com.ucne.gastos.data.repository

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
    fun getGastos(selectedUser: Int?): Flow<Resource<List<GastosDto>>> = flow {
        try {
            emit(Resource.Loading())

            val gastos = gastosApi.getGastos(selectedUser)

            emit(Resource.Success(gastos))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun getTicketById(gastoId: Int): Flow<Resource<GastosDto>> = flow {
        try {
            emit(Resource.Loading())

            val gastos = gastosApi.getGastoById(gastoId)

            emit(Resource.Success(gastos))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}

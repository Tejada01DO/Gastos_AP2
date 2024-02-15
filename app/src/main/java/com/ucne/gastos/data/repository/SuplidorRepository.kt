package com.ucne.gastos.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.ucne.gastos.data.remote.GastosApi
import com.ucne.gastos.data.remote.dto.SuplidorDto
import com.ucne.gastos.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class SuplidorRepository @Inject constructor(
    private val gastosApi: GastosApi
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getSuplidores(): Flow<Resource<List<SuplidorDto>>> = flow{
        try {
            emit(Resource.Loading())

            val suplidor = gastosApi.getSuplidores()

            emit(Resource.Success(suplidor))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}
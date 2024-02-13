package com.ucne.gastos.ui.Consulta

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.gastos.data.remote.dto.GastosDto
import com.ucne.gastos.ui.home.HomeViewModel
import com.ucne.gastos.ui.theme.GastosTheme
import kotlin.time.Duration.Companion.seconds

var showModal by mutableStateOf(false)
var selectedGastos by mutableStateOf(GastosDto())
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun C_Gastos(viewModel: HomeViewModel = hiltViewModel()){
    val uiState by viewModel.state.collectAsState()
    GastosTheme{
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(uiState.isLoading)
                CircularProgressIndicator(modifier = Modifier
                    .size(80.dp)
                    .padding(0.dp, 50.dp), strokeWidth = 8.dp)
        }

        LazyColumn (
            Modifier.fillMaxSize()
        ){
            item {
                Spacer(modifier = Modifier.padding(0.dp, 10.dp))
            }
            uiState.gastos?.forEach { gastos ->
                item {
                    ListGastos(gastos = gastos)
                }
            }
            item {
                if(showModal){
                    DetailModal(gastos = selectedGastos)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListGastos(gastos: GastosDto){

    ElevatedCard(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { ShowDetails(gastos) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "#: ${gastos.idGasto}")
                Text(text = "Suplidor: ${gastos.suplidor}")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = "Descuento: ${gastos.descuento}")
                Text(text = "Itbis: ${gastos.itbis}")
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailModal(gastos: GastosDto){
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {         }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .padding(10.dp, 0.dp)

        ){
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp, 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    IconButton(onClick = { showModal = false }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Salir")
                    }
                }
                Text(text = "Detalle del gasto:")
                Text(text = "#: ${gastos.idGasto}")
                Text(text = "Fecha: ${gastos.fecha.substring(0, 10)}")
                Text(text = "Suplidor #: ${gastos.idSuplidor}")
                Text(text = "Suplidor: ${gastos.suplidor}")
                Text(text = "Nfc: ${gastos.ncf}")
                Text(text = "Concepto: ${gastos.concepto}")
                Text(text = "Descuento: ${gastos.descuento}")
                Text(text = "Itbis: ${gastos.itbis}")
                Text(text = "Monto: ${gastos.monto}")
            }
        }
    }
}


fun ShowDetails(gastos: GastosDto){
    showModal = true
    selectedGastos = gastos
}
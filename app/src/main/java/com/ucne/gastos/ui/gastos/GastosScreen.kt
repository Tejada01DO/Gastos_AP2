package com.ucne.gastos.ui.gastos

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.gastos.data.remote.dto.GastosDto

@Composable
fun GastosScreen(
    viewModel: GastosViewModel = hiltViewModel()
) {
    RegistroGastos(viewModel = viewModel)
}



@Composable
private fun RegistroGastos(viewModel: GastosViewModel) {
    var idGasto by remember { mutableStateOf("0") }
    var fecha by remember { mutableStateOf("") }
    var idSuplidor by remember { mutableStateOf("0") }
    var suplidor by remember { mutableStateOf("") }
    var ncf by remember { mutableStateOf("") }
    var concepto by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("0") }
    var itbis by remember { mutableStateOf("0") }
    var monto by remember { mutableStateOf("0") }
    val stateVertical = rememberScrollState(0)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = stateVertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Gastos",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp)
            )

            OutlinedTextField(
                value = idGasto,
                onValueChange = { idGasto = it },
                label = { Text(text = "Gasto ID: ") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),

                )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text(text = "Fecha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = idSuplidor,
                onValueChange = { idSuplidor = it },
                label = { Text(text = "Suplidor ID: ") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            )

            OutlinedTextField(
                value = suplidor,
                onValueChange = { suplidor = it },
                label = { Text(text = "Suplidor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),


                )

            OutlinedTextField(
                value = ncf,
                onValueChange = { ncf = it },
                label = { Text(text = "NCF") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),

                )

            OutlinedTextField(
                value = concepto,
                onValueChange = { concepto = it },
                label = { Text(text = "Concepto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),

                )
            OutlinedTextField(
                value = descuento,
                onValueChange = { descuento = it },
                label = { Text(text = "Descuento") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),

                )
            OutlinedTextField(
                value = itbis,
                onValueChange = { itbis = it },
                label = { Text(text = "ITBIS") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),

            )
            OutlinedTextField(
                value = monto,
                onValueChange = { monto = it },
                label = { Text(text = "Monto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.postGastos(
                            GastosDto(
                                idGasto.toInt(),
                                fecha,
                                idSuplidor.toInt(),
                                suplidor,
                                ncf,
                                concepto,
                                descuento.toInt(),
                                itbis.toInt(),
                                monto.toInt()
                            )
                        )
                        idGasto = "0"
                        fecha = ""
                        idSuplidor = "0"
                        suplidor = ""
                        ncf = ""
                        concepto = ""
                        descuento = "0"
                        itbis = "0"
                        monto = "0"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Button(
                    onClick = {
                        idGasto = "0"
                        fecha = ""
                        idSuplidor = "0"
                        suplidor = ""
                        ncf = ""
                        concepto = ""
                        descuento = "0"
                        itbis = "0"
                        monto = "0"
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Limpiar")
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
        }
    }
}

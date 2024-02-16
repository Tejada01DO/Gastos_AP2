package com.ucne.gastos.ui.gastos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun GastosScreen(
    viewModel: GastosViewModel = hiltViewModel()
) {
    val stateVertical = rememberScrollState(0)
    val state by viewModel.state.collectAsStateWithLifecycle()
    val gasto = state.gasto

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
                    .padding(bottom = 16.dp)
            )

            state.successMessage?.let {
                Text(text = it)
            }

            state.error?.let{
                Text(text = it)
            }

            OutlinedTextField(
                value = gasto.fecha,
                onValueChange = { viewModel.onEvent(GastosEvent.FechaChanged(it)) },
                label = { Text(text = "Fecha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            OutlinedTextField(
                value = gasto.idSuplidor.toString(),
                onValueChange = { viewModel.onEvent(GastosEvent.IdSuplidorChanged(it)) },
                label = { Text(text = "Suplidor ID: ") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            )

            OutlinedTextField(
                value = gasto.ncf,
                onValueChange = { viewModel.onEvent(GastosEvent.NcfChanged(it)) },
                label = { Text(text = "NCF") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),

                )

            OutlinedTextField(
                value = gasto.concepto,
                onValueChange = { viewModel.onEvent(GastosEvent.ConceptoChanged(it)) },
                label = { Text(text = "Concepto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),

                )
            OutlinedTextField(
                value = gasto.descuento.toString(),
                onValueChange = { viewModel.onEvent(GastosEvent.DescuentoChanged(it)) },
                label = { Text(text = "Descuento") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),

                )
            OutlinedTextField(
                value = gasto.itbis.toString(),
                onValueChange = { viewModel.onEvent(GastosEvent.ItbisChanged(it)) },
                label = { Text(text = "ITBIS") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),

                )
            OutlinedTextField(
                value = gasto.monto.toString(),
                onValueChange = { viewModel.onEvent(GastosEvent.MontoChanged(it)) },
                label = { Text(text = "Monto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(GastosEvent.onSave)
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
                       viewModel.onEvent(GastosEvent.onLimpiar)
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
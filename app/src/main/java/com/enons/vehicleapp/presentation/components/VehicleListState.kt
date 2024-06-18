package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.data.local.model.Vehicles

@Composable
fun VehicleListContent(
    vehiclesList: State<List<Vehicles>>,
    contentPadding: PaddingValues,
    onItemClick: (Vehicles) -> Unit,
    onEditClick: (Vehicles) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(contentPadding),
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(vehiclesList.value.count()) { index ->
                val vehicle = vehiclesList.value[index]
                VehicleCard(
                    vehicle = vehicle,
                    onItemClick = { onItemClick(vehicle) },
                    onEditClick = { onEditClick(vehicle) }
                )
            }
        }
    )
}

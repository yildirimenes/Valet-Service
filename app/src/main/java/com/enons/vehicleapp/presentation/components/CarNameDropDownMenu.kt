package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R
import com.enons.vehicleapp.data.remote.model.CarBrand

@Composable
fun CarNameDropdown(
    carList: List<CarBrand>,
    modifier: Modifier = Modifier,
    initialBrand: String? = null,
    initialModel: String? = null,
    onSelectionChanged: (String, String) -> Unit
) {
    var brandDropDownControl by remember { mutableStateOf(false) }
    var modelDropDownControl by remember { mutableStateOf(false) }
    var selectedBrandIndex by remember { mutableIntStateOf(0) }
    var selectedModelIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(carList, initialBrand, initialModel) {
        if (carList.isNotEmpty()) {
            val initBrand = initialBrand?.trim() ?: ""
            val initModel = initialModel?.trim() ?: ""

            selectedBrandIndex = carList.indexOfFirst {
                it.brand.trim().equals(initBrand, ignoreCase = true)
            }.let { if (it == -1) 0 else it }

            selectedModelIndex = carList[selectedBrandIndex].models.indexOfFirst {
                it.trim().equals(initModel, ignoreCase = true)
            }.let { if (it == -1) 0 else it }

            onSelectionChanged(
                carList[selectedBrandIndex].brand.trim(),
                carList[selectedBrandIndex].models.getOrElse(selectedModelIndex) { "" }.trim()
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedCard(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clickable(enabled = carList.isNotEmpty()) { brandDropDownControl = true }
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = carList.getOrNull(selectedBrandIndex)?.brand
                        ?: stringResource(R.string.brand_placeholder)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = brandDropDownControl && carList.isNotEmpty(),
                onDismissRequest = { brandDropDownControl = false },
                modifier = Modifier
                    .requiredSizeIn(maxHeight = 300.dp)
                    .background(Color.White)
            ) {
                carList.forEachIndexed { index, brand ->
                    DropdownMenuItem(
                        text = { Text(text = brand.brand) },
                        onClick = {
                            brandDropDownControl = false
                            selectedBrandIndex = index
                            selectedModelIndex = 0
                            onSelectionChanged(brand.brand, brand.models.getOrElse(0) { "" })
                        }
                    )
                }
            }
        }

        OutlinedCard(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .clickable(enabled = carList.isNotEmpty()) { modelDropDownControl = true }
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = carList
                        .getOrNull(selectedBrandIndex)
                        ?.models
                        ?.getOrNull(selectedModelIndex)
                        ?: stringResource(R.string.model_placeholder)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = modelDropDownControl && carList.isNotEmpty(),
                onDismissRequest = { modelDropDownControl = false },
                modifier = Modifier
                    .requiredSizeIn(maxHeight = 300.dp)
                    .background(Color.White)
            ) {
                carList.getOrNull(selectedBrandIndex)?.models?.forEachIndexed { index, model ->
                    DropdownMenuItem(
                        text = { Text(text = model) },
                        onClick = {
                            modelDropDownControl = false
                            selectedModelIndex = index
                            onSelectionChanged(carList[selectedBrandIndex].brand, model)
                        }
                    )
                }
            }
        }
    }
}
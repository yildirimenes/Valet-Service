package com.yildirim.vehicleapp.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun UpdateOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun RegisterOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    maxLines: Int = 1
) {
    val maxLenght = 30
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLenght) {
                onValueChange(newText)
            }
        },
        label = label,
        shape = RoundedCornerShape(12.dp),
        maxLines = maxLines
    )
}


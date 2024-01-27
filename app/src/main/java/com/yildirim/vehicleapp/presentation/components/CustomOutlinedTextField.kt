package com.yildirim.vehicleapp.presentation.components
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
    value: String, onValueChange: (String) -> Unit, label: @Composable () -> Unit, maxLines: Int = 1
) {
    val maxLength = 30
    OutlinedTextField(
        value = value, onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChange(newText)
            }
        }, label = label, shape = RoundedCornerShape(12.dp), maxLines = maxLines
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@Composable
fun RegisterOutlinedNumberPlateTextField(
    value: String, onValueChange: (String) -> Unit, label: @Composable () -> Unit, maxLines: Int = 1
) {
    val maxLength = 30
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChange(newText)
            }
        },
        label = label,
        shape = RoundedCornerShape(12.dp),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions.Default,

        )
}

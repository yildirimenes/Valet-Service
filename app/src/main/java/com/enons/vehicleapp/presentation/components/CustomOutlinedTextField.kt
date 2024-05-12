package com.enons.vehicleapp.presentation.components
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import java.util.Locale

@ExperimentalComposeUiApi
@Composable
fun UpdateOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
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
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@ExperimentalComposeUiApi
@Composable
fun RegisterOutlinedTextField(
    value: String, onValueChange: (String) -> Unit, label: @Composable () -> Unit
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
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@ExperimentalComposeUiApi
@Composable
fun RegisterOutlinedNumberPlateTextField(
    value: String, onValueChange: (String) -> Unit, label: @Composable () -> Unit
) {
    val maxLength = 10
    OutlinedTextField(
        modifier = Modifier
            .width(200.dp),
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChange(newText.uppercase(Locale.ROOT))

            }
        },
        label = label,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions.Default
    )
}

@ExperimentalComposeUiApi
@Composable
fun UpdateOutlinedNumberPlateTextField(
    value: String, onValueChange: (String) -> Unit, label: @Composable () -> Unit
) {
    val maxLength = 15
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onValueChange(newText)
            }
        },
        label = label,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions.Default
    )
}

@ExperimentalComposeUiApi
@Composable
fun UpdateOutlinedLocationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
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
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
    )
}
@ExperimentalComposeUiApi
@Composable
fun HourlyFeeOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit
) {
    val maxLength = 30
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText ->
            if (newText.length <= maxLength && newText.isDigitsOnly()) {
                onValueChange(newText)
            }
        },
        label = label,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

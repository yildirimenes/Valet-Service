package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.enons.vehicleapp.R
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
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit
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

@Composable
fun EmailAuthOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    focusedTextColor: Color = Color.DarkGray,
    unfocusedTextColor: Color = Color.DarkGray,
    focusedContainerColor: Color = Color.Transparent,
    unfocusedContainerColor: Color = Color.Transparent,
    focusedIndicatorColor: Color = Color.DarkGray,
    unfocusedIndicatorColor: Color = Color.LightGray
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor
        )
    )
}

@Composable
fun PasswordOutlinedTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    isPasswordFocused: Boolean,
    onFocusChange: (FocusState) -> Unit,
    focusedTextColor: Color = Color.DarkGray,
    unfocusedTextColor: Color = Color.DarkGray,
    focusedContainerColor: Color = Color.Transparent,
    unfocusedContainerColor: Color = Color.Transparent,
    focusedIndicatorColor: Color = Color.DarkGray,
    unfocusedIndicatorColor: Color = Color.LightGray
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = label,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState -> onFocusChange(focusState) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        trailingIcon = {
            val image = if (passwordVisible) {
                painterResource(id = R.drawable.baseline_visibility_24)
            } else {
                painterResource(id = R.drawable.baseline_visibility_off_24)
            }

            Icon(
                painter = image,
                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                modifier = Modifier
                    .clickable { onPasswordVisibilityChange() }
                    .alpha(if (isPasswordFocused) 1f else 0f)
            )
        },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor
        )
    )
}


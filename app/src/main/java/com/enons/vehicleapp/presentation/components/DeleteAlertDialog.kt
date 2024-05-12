package com.enons.vehicleapp.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.enons.vehicleapp.R

@Composable
fun DeleteAlertDialog(
    modifier: Modifier = Modifier,
    isDeleteDialogVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (isDeleteDialogVisible) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() },
            title = {
                Text(stringResource(id = R.string.delete))
            },
            text = {
                Text(stringResource(id = R.string.delete_operation))
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                ) {
                    Text(stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text(stringResource(id = R.string.dismiss))
                }
            }
        )
    }
}

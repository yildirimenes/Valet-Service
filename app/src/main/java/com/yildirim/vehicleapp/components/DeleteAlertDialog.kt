package com.yildirim.vehicleapp.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yildirim.vehicleapp.R

@Composable
fun DeleteAlertDialog(
    isDeleteDialogVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (isDeleteDialogVisible) {
        AlertDialog(
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

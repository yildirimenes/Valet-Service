package com.enons.vehicleapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.enons.vehicleapp.R
@Composable
fun DeleteAlertDialog(
    modifier: Modifier = Modifier,
    isDeleteDialogVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    @StringRes titleRes: Int = R.string.delete,
    @StringRes messageRes: Int = R.string.delete_operation,
) {
    if (isDeleteDialogVisible) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() },
            title = {
                Text(stringResource(id = titleRes))
            },
            text = {
                Text(stringResource(id = messageRes))
            },
            confirmButton = {
                AlertDialogBtn(
                    text = stringResource(id = R.string.confirm),
                    onClick = {
                        onConfirm()
                        onDismiss()
                    }
                )
            },
            dismissButton = {
                AlertDialogBtn(
                    containerColor = colorResource(R.color.color_3),
                    contentColor = Color.LightGray,
                    text = stringResource(id = R.string.dismiss),
                    onClick = { onDismiss() }
                )
            }
        )
    }
}

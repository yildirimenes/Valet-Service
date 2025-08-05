import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.AlertDialogIconBtn

@Composable
fun DeliveryAlertDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onDeliveryWithMessage: () -> Unit,
    onDelivery: () -> Unit,
) {
    if (isVisible) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = stringResource(id = R.string.vehicle_delivery),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(stringResource(id = R.string.vehicle_delivery_question))

                    Spacer(modifier = Modifier.height(8.dp))

                    AlertDialogIconBtn(
                        text = stringResource(id = R.string.fast_delivery),
                        iconRes = R.drawable.outline_delivery_truck_speed_24,
                        onClick = {
                            onDelivery()
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AlertDialogIconBtn(
                        text = stringResource(id = R.string.message_delivery),
                        iconRes = R.drawable.outline_message_24,
                        onClick = {
                            onDeliveryWithMessage()
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}

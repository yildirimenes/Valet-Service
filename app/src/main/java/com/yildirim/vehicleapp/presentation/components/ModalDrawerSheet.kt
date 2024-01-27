package com.yildirim.vehicleapp.presentation.components
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yildirim.vehicleapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.car_settings)) })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 70.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            CustomSettingsCard(
                iconRes = R.drawable.baseline_workspace_premium_24,
                text = stringResource(id = R.string.valet_premium),
                onClick = {
                    context.sendMail(to = "oyildirim.enes@gmail.com", subject = "Premium Hesap Bilgisi")
                })
            CustomSettingsCard(
                iconRes = R.drawable.baseline_language_24,
                text = stringResource(id = R.string.language_option),
                onClick = {})
            CustomSettingsCard(
                iconRes = R.drawable.baseline_thumb_up_alt_24,
                text = stringResource(id = R.string.rate_us),
                onClick = {})
        }
    }
}

fun Context.sendMail(to: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}
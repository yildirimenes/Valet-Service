package com.enons.vehicleapp.domain.useCase

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import javax.inject.Inject

class SendMailUseCase @Inject constructor() {
    fun execute(context: Context, to: String, subject: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$to")
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No email app available", Toast.LENGTH_LONG).show()
        }
    }
}

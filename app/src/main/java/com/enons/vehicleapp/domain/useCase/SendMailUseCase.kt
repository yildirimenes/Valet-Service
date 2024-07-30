package com.enons.vehicleapp.domain.useCase

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import javax.inject.Inject

class SendMailUseCase @Inject constructor() {
    fun execute(context: Context, to: String, subject: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // TODO: Handle case where no email app is available
        }
    }
}

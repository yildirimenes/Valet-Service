package com.enons.vehicleapp.domain.useCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import javax.inject.Inject

class MakePhoneCallUseCase @Inject constructor() {
    fun execute(customerPhone: String, context: Context) {
        try {
            val formattedPhone = "0$customerPhone"
            val intent = Intent(Intent.ACTION_DIAL)
            val phoneUri = Uri.parse("tel:$formattedPhone")
            intent.data = phoneUri
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error making phone call", Toast.LENGTH_LONG).show()
        }
    }
}

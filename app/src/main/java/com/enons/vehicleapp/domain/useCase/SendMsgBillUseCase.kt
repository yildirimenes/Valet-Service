package com.enons.vehicleapp.domain.useCase

import android.content.Context
import android.content.Intent
import android.widget.Toast
import javax.inject.Inject

class SendMsgBillUseCase @Inject constructor() {
    fun execute(context: Context, customerName: String, phoneNumber: String) {
        val formattedPhone = "0$phoneNumber"
        val messages ="Dear $customerName, your vehicle has been delivered."

        try {
            val smsIntent = Intent(Intent.ACTION_SEND)
            smsIntent.type = "text/plain"
            smsIntent.putExtra(Intent.EXTRA_TEXT, messages)
            smsIntent.putExtra("address", formattedPhone)
            context.startActivity(Intent.createChooser(smsIntent, "Mesaj Gönder"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
        }
    }
}

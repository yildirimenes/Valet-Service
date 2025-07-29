package com.enons.vehicleapp.domain.useCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import javax.inject.Inject

class SendMsgBillUseCase @Inject constructor() {
    fun execute(context: Context, customerName: String, phoneNumber: String) {
        val formattedPhone = "0$phoneNumber"
        val messages = "Dear $customerName, your vehicle has been delivered."

        try {
            val uri = Uri.parse("smsto:$formattedPhone")
            val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
            smsIntent.putExtra("sms_body", messages)
            context.startActivity(smsIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
        }
    }
}

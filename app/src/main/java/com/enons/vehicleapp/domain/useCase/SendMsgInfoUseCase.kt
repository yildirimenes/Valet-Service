package com.enons.vehicleapp.domain.useCase

import android.content.Context
import android.content.Intent
import android.widget.Toast
import javax.inject.Inject

class SendMsgInfoUseCase @Inject constructor() {
    fun execute(
        context: Context,
        customerName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        phoneNumber: String,
        currentHours: String
    ) {
        val formattedPhone = "0$phoneNumber"
        val message = "Sn. $customerName $vehicleNumberPlate plakalı aracınız $currentHours saatinde $vehicleLocationDescription lokasyonunda teslim alınmıştır."

        try {
            val smsIntent = Intent(Intent.ACTION_SEND)
            smsIntent.type = "text/plain"
            smsIntent.putExtra(Intent.EXTRA_TEXT, message)
            smsIntent.putExtra("address", formattedPhone)
            context.startActivity(Intent.createChooser(smsIntent, "Mesaj Gönder"))
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
        }
    }
}

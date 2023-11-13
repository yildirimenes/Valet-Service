package com.yildirim.vehicleapp.viewmodel

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository
import java.text.SimpleDateFormat
import java.util.Locale

class VehicleViewModel(application: Application) : AndroidViewModel(application){
    var vrepo = VehiclesDaoRepository(application)

    fun makePhoneCall(customerPhone: String, context: Context) {
        val formattedPhone = "0$customerPhone"
        val intent = Intent(Intent.ACTION_CALL)
        val phoneUri = Uri.parse("tel:$formattedPhone")
        intent.data = phoneUri
        context.startActivity(intent)
    }

    fun sendMessage(context: Context, customerName: String, vehicleNumberPlate: String, vehicleLocationDescription: String, phoneNumber: String, currentDate:String, currentHours:String) {
        val message = "Sn. $customerName $vehicleNumberPlate plakalı aracınız $currentHours saatinde $vehicleLocationDescription lokasyonunda teslim alınmıştır."
        val permission = Manifest.permission.SEND_SMS

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            val smsManager = android.telephony.SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
        }
    }

    fun calculateTimeDifference(startDateText: String): String {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val endDateText = simpleDateFormat.format(System.currentTimeMillis())

        val date1 = simpleDateFormat.parse(startDateText)
        val date2 = simpleDateFormat.parse(endDateText)

        return if (date1 != null && date2 != null) {
            var different = date2.time - date1.time

            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24

            var elapsedDays = different / daysInMilli
            different %= daysInMilli

            var elapsedHours = different / hoursInMilli
            different %= hoursInMilli

            var elapsedMinutes = different / minutesInMilli
            different %= minutesInMilli

            "$elapsedDays days, $elapsedHours hours, $elapsedMinutes minutes"
        } else {
            "Invalid Operation"
        }
    }
}

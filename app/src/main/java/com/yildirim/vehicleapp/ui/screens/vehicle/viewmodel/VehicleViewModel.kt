package com.yildirim.vehicleapp.ui.screens.vehicle.viewmodel
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
import com.yildirim.vehicleapp.data.repository.VehiclesDaoRepository
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

    fun sendMessage(context: Context, customerName: String, vehicleNumberPlate: String, vehicleLocationDescription: String, phoneNumber: String, currentHours: String) {
        try {
            val message = "Sn. $customerName $vehicleNumberPlate plakalı aracınız $currentHours saatinde $vehicleLocationDescription lokasyonunda teslim alınmıştır."
            val permission = Manifest.permission.SEND_SMS

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                val smsManager = android.telephony.SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
        }
    }
    fun msgBillButton(context: Context, customerName: String, phoneNumber: String) {
        try {
            val message = "Sn. $customerName aracınız teslim edilmiştir."
            val permission = Manifest.permission.SEND_SMS

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                val smsManager = android.telephony.SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
            } else {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
        }
    }
    fun calculateTimeDifference(startDateText: String): Pair<String, Any> {
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

            val elapsedDays = different / daysInMilli
            different %= daysInMilli

            val elapsedHours = different / hoursInMilli
            different %= hoursInMilli

            val elapsedMinutes = different / minutesInMilli
            different %= minutesInMilli

            var totalAmount = 0
            var timeDifference = ""
            if (elapsedDays > 0){
                totalAmount = (120*elapsedDays).toInt()
                timeDifference = "$elapsedDays gün, $elapsedHours saat"

            }
            else if (elapsedHours >= 0 && elapsedHours < 1) {
                totalAmount = 36
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else if (elapsedHours >= 1 && elapsedHours < 2) {
                totalAmount = 46
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"

            }
            else if (elapsedHours >= 2 && elapsedHours < 4) {
                totalAmount = 50
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else if (elapsedHours >= 4 && elapsedHours < 8) {
                totalAmount = 70
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else if (elapsedHours >= 8 && elapsedHours < 12) {
                totalAmount = 90
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else if (elapsedHours >= 12 && elapsedHours < 24) {
                totalAmount = 120
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else{
                println("Invalid Operation")
            }
            val price = "$totalAmount ₺"
            Pair(timeDifference, price)

        } else {
            Pair("Invalid Operation", 0)
        }
    }
}

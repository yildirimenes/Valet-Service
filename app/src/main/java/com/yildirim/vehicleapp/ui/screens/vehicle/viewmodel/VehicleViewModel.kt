package com.yildirim.vehicleapp.ui.screens.vehicle.viewmodel
import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.telephony.SmsManager
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
        try {
            val formattedPhone = "0$customerPhone"
            val intent = Intent(Intent.ACTION_CALL)
            val phoneUri = Uri.parse("tel:$formattedPhone")
            intent.data = phoneUri

            val permission = Manifest.permission.CALL_PHONE

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error making phone call", Toast.LENGTH_LONG).show()
        }
    }

    fun sendMessage(context: Context, customerName: String, vehicleNumberPlate: String, vehicleLocationDescription: String, phoneNumber: String, currentHours: String) {
        val formattedPhone = "0$phoneNumber"
        try {
            val message = "Sn. $customerName $vehicleNumberPlate plakalı aracınız $currentHours saatinde $vehicleLocationDescription lokasyonunda teslim alınmıştır."
            val permission = Manifest.permission.SEND_SMS

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(formattedPhone, null, message, null, null)
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
        val formattedPhone = "0$phoneNumber"
        try {
            val messages = "Sn. $customerName aracınız teslim edilmiştir."
            val permission = Manifest.permission.SEND_SMS

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(formattedPhone, null, messages, null, null)
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
                totalAmount = 48
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"

            }
            else if (elapsedHours >= 2 && elapsedHours < 4) {
                totalAmount = 60
                timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
            }
            else if (elapsedHours >= 4 && elapsedHours < 8) {
                totalAmount = 72
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

package com.yildirim.vehicleapp.presentation.screens.vehicle.viewmodel
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yildirim.vehicleapp.data.model.HourlyFee
import com.yildirim.vehicleapp.data.repository.VehiclesDaoRepository
import java.text.SimpleDateFormat
import java.util.Locale

@Suppress("DEPRECATION")
class VehicleViewModel(application: Application) : AndroidViewModel(application) {
    private var vrepo = VehiclesDaoRepository(application)
    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()

    init {
        load()
        hourlyFeeList = vrepo.getHourlyFee()
    }

    fun load() {
        vrepo.getAllHourlyFee()
    }

    fun makePhoneCall(customerPhone: String, context: Context) {
        try {
            val formattedPhone = "0$customerPhone"
            if (formattedPhone != null) {
                val intent = Intent(Intent.ACTION_DIAL)
                val phoneUri = Uri.parse("tel:$formattedPhone")
                intent.data = phoneUri
                context.startActivity(intent)
            }
            else{
                Toast.makeText(context, "Error making phone call", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error making phone call", Toast.LENGTH_LONG).show()
        }
    }

    fun sendMessage(
        context: Context,
        customerName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        phoneNumber: String,
        currentHours: String
    ) {
        val formattedPhone = "0$phoneNumber"
        val message =
            "Sn. $customerName $vehicleNumberPlate plakalı aracınız $currentHours saatinde $vehicleLocationDescription lokasyonunda teslim alınmıştır."

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

    fun msgBillButton(context: Context, customerName: String, phoneNumber: String) {
        val formattedPhone = "0$phoneNumber"
        val messages = "Sn. $customerName aracınız teslim edilmiştir."

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

    fun calculateTimeDifference(startDateText: String, hourly1: MutableState<String>, hourly2: MutableState<String>, hourly3: MutableState<String>, hourly4: MutableState<String>, hourly5: MutableState<String>, daily: MutableState<String>): Pair<String, Any> {
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
            if (elapsedDays > 0) {
                try {
                    totalAmount = (daily.value.toInt() * elapsedDays).toInt()
                    timeDifference = "$elapsedDays gün, $elapsedHours saat"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 0 && elapsedHours < 1) {
                try {
                    totalAmount = hourly1.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 1 && elapsedHours < 2) {
                try {
                    totalAmount = hourly2.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 2 && elapsedHours < 4) {
                try {
                    totalAmount = hourly3.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 4 && elapsedHours < 8) {
                try {
                    totalAmount = hourly4.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 8 && elapsedHours < 12) {
                try {
                    totalAmount = hourly5.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 12 && elapsedHours < 24) {
                try {
                    totalAmount = daily.value.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else {
                println("Invalid Operation")
            }
            val price = "$totalAmount ₺"
            Pair(timeDifference, price)

        } else {
            Pair("Invalid Operation", 0)
        }
    }
}

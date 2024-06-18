package com.enons.vehicleapp.presentation.screens.VehiclePage.viewmodel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class VehiclePageViewModel @Inject constructor(private val repository: VehiclesDaoRepository) : ViewModel() {
    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()
    init {
        load()
        hourlyFeeList = repository.getHourlyFee()
    }

    fun load() {
        repository.getAllHourlyFee()
    }

    fun delete(vehicleId: Int) {
        repository.delVehicle(vehicleId)
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

    fun calculateTimeDifference(startDateText: String, hourly1: String, hourly2: String, hourly3: String, hourly4: String, hourly5: String, daily: String): Pair<String, Any> {
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
                    totalAmount = (daily.toInt() * elapsedDays).toInt()
                    timeDifference = "$elapsedDays gün, $elapsedHours saat"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 0 && elapsedHours < 1) {
                try {
                    totalAmount = hourly1.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 1 && elapsedHours < 2) {
                try {
                    totalAmount = hourly2.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 2 && elapsedHours < 4) {
                try {
                    totalAmount = hourly3.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 4 && elapsedHours < 8) {
                try {
                    totalAmount = hourly4.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 8 && elapsedHours < 12) {
                try {
                    totalAmount = hourly5.toInt()
                    timeDifference = "$elapsedHours saat, $elapsedMinutes dakika"
                } catch (e: NumberFormatException) {
                    println("Invalid number format")
                }
            } else if (elapsedHours >= 12 && elapsedHours < 24) {
                try {
                    totalAmount = daily.toInt()
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

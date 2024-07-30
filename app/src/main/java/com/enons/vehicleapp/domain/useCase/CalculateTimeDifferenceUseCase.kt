package com.enons.vehicleapp.domain.useCase

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class CalculateTimeDifferenceUseCase @Inject constructor() {
    fun execute(
        startDateText: String,
        hourly1: String,
        hourly2: String,
        hourly3: String,
        hourly4: String,
        hourly5: String,
        daily: String
    ): Pair<String, Any> {
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

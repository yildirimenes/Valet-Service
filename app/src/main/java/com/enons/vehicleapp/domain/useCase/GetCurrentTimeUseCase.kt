package com.enons.vehicleapp.domain.useCase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCurrentTimeUseCase @Inject constructor() {
    operator fun invoke(): String {
        val currentDateTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(currentDateTime)
    }
}

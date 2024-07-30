package com.enons.vehicleapp.domain.useCase

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class GetCurrentDateUseCase @Inject constructor() {
    operator fun invoke(): String {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(currentDateTime)
    }
}

package com.enons.vehicleapp.domain.useCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class RouteUrlLinkUseCase @Inject constructor() {
    fun execute(activityContext: Context, appURL: String) {
        val playIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(appURL)
        }
        try {
            activityContext.startActivity(playIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

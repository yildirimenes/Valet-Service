package com.enons.vehicleapp.presentation.screens.navigation_drawer.viewmodel
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel

class NavigationDrawerViewModel(application: Application) : AndroidViewModel(application) {
    fun openPlayStore(activityContext: Context, appURL: String) {
        val playIntent: Intent = Intent().apply {

            action = Intent.ACTION_VIEW

            data = Uri.parse(appURL)

        }
        try {
            activityContext.startActivity(playIntent)
        } catch (e: Exception) {
            // handle the exception
        }
    }

    fun sendMail(context: Context, to: String, subject: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.item/email" // or "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // TODO: Handle case where no email app is available
        } catch (t: Throwable) {
            // TODO: Handle potential other types of exceptions
        }
    }
}


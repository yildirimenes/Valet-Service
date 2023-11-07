package com.yildirim.vehicleapp.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository

class VehicleViewModel(application: Application) : AndroidViewModel(application){
    var vrepo = VehiclesDaoRepository(application)

    fun makePhoneCall(customerPhone: String, context: Context) {
        val formattedPhone = "0$customerPhone"
        val intent = Intent(Intent.ACTION_CALL)
        val phoneUri = Uri.parse("tel:$formattedPhone")
        intent.data = phoneUri
        context.startActivity(intent)
    }

}
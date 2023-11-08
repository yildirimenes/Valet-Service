package com.yildirim.vehicleapp.viewmodel

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

    fun sendMessage(context: Context, customerName: String, vehicleNumberPlate: String, vehicleLocationDescription: String, phoneNumber: String) {
        val message = "Sn. $customerName $vehicleNumberPlate plakalı aracınız $vehicleLocationDescription lokasyonundadır"
        val permission = Manifest.permission.SEND_SMS

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            val smsManager = android.telephony.SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
        }
    }
}
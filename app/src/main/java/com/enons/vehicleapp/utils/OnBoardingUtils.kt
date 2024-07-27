package com.enons.vehicleapp.utils

import android.content.Context

class OnBoardingUtils (private val context: Context){

    fun isOnBoardingComplete():Boolean{
        return context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .getBoolean("completed",false)
    }

    fun setOnBoardingComplete(){
        context.getSharedPreferences("onboarding",Context.MODE_PRIVATE)
            .edit()
            .putBoolean("completed",true)
            .apply()
    }
}
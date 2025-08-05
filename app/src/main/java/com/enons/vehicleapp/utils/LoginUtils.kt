package com.enons.vehicleapp.utils

import android.content.Context

class LoginUtils(context: Context) {
    private val sharedPref = context.getSharedPreferences("login_pref", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean("is_logged_in", false)
    }

    fun setLoggedIn(value: Boolean) {
        sharedPref.edit().putBoolean("is_logged_in", value).apply()
    }

    fun logout() {
        sharedPref.edit().clear().apply()
    }
}

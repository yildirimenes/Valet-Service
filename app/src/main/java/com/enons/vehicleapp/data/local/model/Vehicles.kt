package com.enons.vehicleapp.data.local.model

data class Vehicles(
    var vehicle_id: Int = 0,
    var customer_name: String = "",
    var customer_phone_number: String = "",
    var vehicle_name: String = "",
    var vehicle_number_plate: String = "",
    var vehicle_location_description: String = "",
    var vehicle_check_in_date: String = "",
    var vehicle_check_in_hours: String = "",
    var user_id: String = ""
)
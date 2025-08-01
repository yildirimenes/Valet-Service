package com.enons.vehicleapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.remote.model.CarBrand

interface CarBrandModelRepository {
    fun getCarNames(): MutableLiveData<List<CarBrand>>
    fun fetchCarNames()
}

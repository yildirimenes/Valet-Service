package com.enons.vehicleapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.remote.model.CarBrand
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CarBrandModelRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CarBrandModelRepository {

    private val carNameList = MutableLiveData<List<CarBrand>>()

    override fun getCarNames(): MutableLiveData<List<CarBrand>> = carNameList

    override fun fetchCarNames() {
        firestore.collection("car_brands").get()
            .addOnSuccessListener { result ->
                val list = result.documents.mapNotNull { it.toObject(CarBrand::class.java) }
                carNameList.value = list
            }
            .addOnFailureListener {
                carNameList.value = emptyList()
            }
    }
}
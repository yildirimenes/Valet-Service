package com.enons.vehicleapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.local.dao.DeliveredDao
import com.enons.vehicleapp.data.local.dao.VehiclesDao
import com.enons.vehicleapp.data.local.model.Delivered
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.local.model.Vehicles
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VehiclesRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val dao: VehiclesDao,
    private val deliveredDao: DeliveredDao
) : VehiclesRepository {

    private var vehiclesList = MutableLiveData<List<Vehicles>>()
    private var hourlyFeeList = MutableLiveData<List<HourlyFee>>()
    private var deliveredList = MutableLiveData<List<Delivered>>()

    init {
        vehiclesList = MutableLiveData()
        hourlyFeeList = MutableLiveData()
        deliveredList = MutableLiveData()
    }

    override fun getVehicles(): MutableLiveData<List<Vehicles>> {
        return vehiclesList
    }

    override fun getAllVehicles() {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("vehicles")
            .whereEqualTo("user_id", uid)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    val list = it.documents.mapNotNull { doc ->
                        val vehicleId = doc.getLong("vehicle_id")?.toInt() ?: return@mapNotNull null
                        val customerName = doc.getString("customer_name") ?: return@mapNotNull null
                        val customerPhone =
                            doc.getString("customer_phone_number") ?: return@mapNotNull null
                        val vehicleName = doc.getString("vehicle_name") ?: return@mapNotNull null
                        val vehiclePlate =
                            doc.getString("vehicle_number_plate") ?: return@mapNotNull null
                        val location =
                            doc.getString("vehicle_location_description") ?: return@mapNotNull null
                        val checkInDate =
                            doc.getString("vehicle_check_in_date") ?: return@mapNotNull null
                        val checkInHours =
                            doc.getString("vehicle_check_in_hours") ?: return@mapNotNull null
                        Vehicles(
                            vehicleId,
                            customerName,
                            customerPhone,
                            vehicleName,
                            vehiclePlate,
                            location,
                            checkInDate,
                            checkInHours
                        )
                    }
                    vehiclesList.value = list
                }
            }
    }

    override fun getHourlyFee(): MutableLiveData<List<HourlyFee>> {
        return hourlyFeeList
    }

    override fun getAllHourlyFee() {
        CoroutineScope(Dispatchers.Main).launch {
            hourlyFeeList.value = dao.allHourlyFee()
        }
    }

    override fun searchVehicles(searchPlate: String) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("vehicles")
            .whereEqualTo("user_id", uid)
            .get()
            .addOnSuccessListener { snapshot ->
                val list = snapshot.documents.mapNotNull { doc ->
                    val vehicleId = doc.getLong("vehicle_id")?.toInt() ?: return@mapNotNull null
                    val customerName = doc.getString("customer_name") ?: return@mapNotNull null
                    val customerPhone =
                        doc.getString("customer_phone_number") ?: return@mapNotNull null
                    val vehicleName = doc.getString("vehicle_name") ?: return@mapNotNull null
                    val vehiclePlate =
                        doc.getString("vehicle_number_plate") ?: return@mapNotNull null
                    val location =
                        doc.getString("vehicle_location_description") ?: return@mapNotNull null
                    val checkInDate =
                        doc.getString("vehicle_check_in_date") ?: return@mapNotNull null
                    val checkInHours =
                        doc.getString("vehicle_check_in_hours") ?: return@mapNotNull null
                    Vehicles(
                        vehicleId,
                        customerName,
                        customerPhone,
                        vehicleName,
                        vehiclePlate,
                        location,
                        checkInDate,
                        checkInHours
                    )
                }.filter { it.vehicle_number_plate.contains(searchPlate, ignoreCase = true) }
                vehiclesList.value = list
            }
    }

    override fun registerVehicle(
        customerName: String,
        customerPhoneNumber: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    ) {
        val uid = auth.currentUser?.uid ?: return
        val vehicleId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
        val vehicleMap = hashMapOf(
            "vehicle_id" to vehicleId,
            "customer_name" to customerName,
            "customer_phone_number" to customerPhoneNumber,
            "vehicle_name" to vehicleName,
            "vehicle_number_plate" to vehicleNumberPlate,
            "vehicle_location_description" to vehicleLocationDescription,
            "vehicle_check_in_date" to vehicleCheckInDate,
            "vehicle_check_in_hours" to vehicleCheckInHours,
            "user_id" to uid
        )
        firestore.collection("vehicles").document(vehicleId.toString()).set(vehicleMap)
    }

    override fun updateVehicle(
        vehicleId: Int,
        customerName: String,
        customerPhoneNumber: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    ) {
        val uid = auth.currentUser?.uid ?: return
        val vehicleMap = hashMapOf<String, Any>(
            "customer_name" to customerName,
            "customer_phone_number" to customerPhoneNumber,
            "vehicle_name" to vehicleName,
            "vehicle_number_plate" to vehicleNumberPlate,
            "vehicle_location_description" to vehicleLocationDescription,
            "vehicle_check_in_date" to vehicleCheckInDate,
            "vehicle_check_in_hours" to vehicleCheckInHours,
            "user_id" to uid
        )
        firestore.collection("vehicles").document(vehicleId.toString()).update(vehicleMap)
    }

    override fun updateHourlyFee(
        feeId: Int,
        hourlyV1: Int,
        hourlyV2: Int,
        hourlyV3: Int,
        hourlyV4: Int,
        hourlyV5: Int,
        daily: Int
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            val updateHourlyFee =
                HourlyFee(feeId, hourlyV1, hourlyV2, hourlyV3, hourlyV4, hourlyV5, daily)
            dao.updateHourlyFee(updateHourlyFee)
        }
    }

    override fun delVehicle(personId: Int) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("vehicles")
            .whereEqualTo("user_id", uid)
            .whereEqualTo("vehicle_id", personId)
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.forEach { doc ->
                    doc.reference.delete()
                }
            }
    }

    override fun addDeliveredVehicle(plate: String, price: Int, date: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val delivered = Delivered(0, plate, price, date)
            deliveredDao.addDelivered(delivered)
        }
    }

    override fun getDeliveredVehicles(): MutableLiveData<List<Delivered>> {
        return deliveredList
    }

    override fun getAllDeliveredVehicles() {
        CoroutineScope(Dispatchers.Main).launch {
            deliveredList.value = deliveredDao.allDelivered()
        }
    }

    override fun deleteDeliveredVehicle(deliveredId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            deliveredDao.deleteDelivered(deliveredId)
            getAllDeliveredVehicles()
        }
    }

    override fun deleteAllDelivered() {
        CoroutineScope(Dispatchers.Main).launch {
            deliveredDao.deleteAllDelivered()
            getAllDeliveredVehicles()
        }
    }
}

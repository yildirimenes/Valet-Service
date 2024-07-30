package com.enons.vehicleapp.presentation.screens.vehiclePage.viewmodel
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.domain.useCase.CalculateTimeDifferenceUseCase
import com.enons.vehicleapp.domain.useCase.MakePhoneCallUseCase
import com.enons.vehicleapp.domain.useCase.SendMsgBillUseCase
import com.enons.vehicleapp.domain.useCase.SendMsgInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehiclePageViewModel @Inject constructor(
    private val repository: VehiclesRepository,
    private val makePhoneCallUseCase: MakePhoneCallUseCase,
    private val sendMsgInfoUseCase: SendMsgInfoUseCase,
    private val sendMsgBillUseCase: SendMsgBillUseCase,
    private val calculateTimeDifferenceUseCase: CalculateTimeDifferenceUseCase
) : ViewModel() {

    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()
    init {
        load()
        hourlyFeeList = repository.getHourlyFee()
    }

    fun load() {
        repository.getAllHourlyFee()
    }

    fun delete(vehicleId: Int) {
        repository.delVehicle(vehicleId)
    }

    fun makePhoneCall(customerPhone: String, context: Context) {
        makePhoneCallUseCase.execute(customerPhone, context)
    }

    fun sendMessage(
        context: Context,
        customerName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        phoneNumber: String,
        currentHours: String
    ) {
        sendMsgInfoUseCase.execute(
            context,
            customerName,
            vehicleNumberPlate,
            vehicleLocationDescription,
            phoneNumber,
            currentHours
        )
    }

    fun sendMsgBillUseCase(context: Context, customerName: String, phoneNumber: String) {
        sendMsgBillUseCase.execute(context, customerName, phoneNumber)
    }

    fun calculateTimeDifference(
        startDateText: String,
        hourly1: String,
        hourly2: String,
        hourly3: String,
        hourly4: String,
        hourly5: String,
        daily: String
    ): Pair<String, Any> {
        return calculateTimeDifferenceUseCase.execute(
            startDateText,
            hourly1,
            hourly2,
            hourly3,
            hourly4,
            hourly5,
            daily
        )
    }
}
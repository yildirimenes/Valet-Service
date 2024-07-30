package com.enons.vehicleapp.presentation.screens.drawerSheet.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.domain.useCase.RouteUrlLinkUseCase
import com.enons.vehicleapp.domain.useCase.SendMailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val openPlayStoreUseCase: RouteUrlLinkUseCase,
    private val sendMailUseCase: SendMailUseCase
) : ViewModel() {

    fun openPlayStore(activityContext: Context, appURL: String) {
        openPlayStoreUseCase.execute(activityContext, appURL)
    }

    fun sendMail(context: Context, to: String, subject: String) {
        sendMailUseCase.execute(context, to, subject)
    }
}


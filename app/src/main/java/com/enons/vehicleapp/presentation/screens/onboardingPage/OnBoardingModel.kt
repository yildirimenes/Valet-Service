package com.enons.vehicleapp.presentation.screens.onboardingPage

import androidx.annotation.DrawableRes
import com.enons.vehicleapp.R

sealed class OnBoardingModel (
    @DrawableRes val image:Int,
    val title:Int,
    val description:Int
){
    data object FirstPages : OnBoardingModel(
        image = R.drawable.img1,
        title = R.string.on_boarding_title_1,
        description = R.string.on_boarding_description_1
    )

    data object SecondPages : OnBoardingModel(
        image = R.drawable.img2,
        title = R.string.on_boarding_title_2,
        description = R.string.on_boarding_description_2
    )

    data object ThirdPages : OnBoardingModel(
        image = R.drawable.img3,
        title = R.string.on_boarding_title_3,
        description = R.string.on_boarding_description_3
    )
}

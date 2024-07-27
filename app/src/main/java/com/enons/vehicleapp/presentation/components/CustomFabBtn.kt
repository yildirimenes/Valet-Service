package com.enons.vehicleapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R

@Composable
fun CustomFabBtn(
    modifier: Modifier,
    isVisibleBecauseOfScrolling: Boolean,
    onClick: () -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisibleBecauseOfScrolling,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = fadeOut(
            animationSpec = keyframes {
                this.durationMillis = 120
            }
        )
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = colorResource(id = R.color.light_green),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.add_image),
                    contentDescription = "", tint = Color.White
                )
            }
        )
    }
}
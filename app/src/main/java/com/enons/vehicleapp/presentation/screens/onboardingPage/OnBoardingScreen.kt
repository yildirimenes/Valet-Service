package com.enons.vehicleapp.presentation.screens.onboardingPage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.IndicatorComponent
import com.enons.vehicleapp.presentation.components.OnBoardingBtn
import com.enons.vehicleapp.presentation.components.OnBoardingGraphComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onFinished: () -> Unit
) {

    val pages =
        listOf(OnBoardingModel.FirstPages, OnBoardingModel.SecondPages, OnBoardingModel.ThirdPages)

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Done")
                else -> listOf("", "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (buttonState.value[0].isNotEmpty()) {
                        OnBoardingBtn(
                            text = buttonState.value[0],
                            containerColor = Color.Transparent,
                            contentColor = Color.Gray,
                            onClick = {
                                scope.launch {
                                    if (pagerState.currentPage > 0) {
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    }
                                }
                            }
                        )
                    }
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IndicatorComponent(pageSize = pages.size, currentPage = pagerState.currentPage)

                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    OnBoardingBtn(
                        text = buttonState.value[1],
                        containerColor = colorResource(id = R.color.dark_green),
                        contentColor = colorResource(id = R.color.color_3),
                        onClick = {
                            scope.launch {
                                if (pagerState.currentPage < pages.size -1){
                                    pagerState.animateScrollToPage(pagerState.currentPage +1)
                                }else{
                                    onFinished()
                                }
                            }
                        }
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                HorizontalPager(state = pagerState) { index ->
                    OnBoardingGraphComponent(onBoardingModel = pages[index])
                }
            }
        }
    )
}

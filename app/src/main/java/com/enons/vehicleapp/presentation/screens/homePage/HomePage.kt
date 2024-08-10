package com.enons.vehicleapp.presentation.screens.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CustomFabBtn
import com.enons.vehicleapp.presentation.components.SearchTextField
import com.enons.vehicleapp.presentation.components.VehicleListContent
import com.enons.vehicleapp.presentation.screens.drawerSheet.DrawerSheet
import com.enons.vehicleapp.presentation.screens.homePage.viewmodel.HomepageViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val tf = remember { mutableStateOf("") }
    var isCall by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyGridState()
    val fabVisibility by derivedStateOf { listState.firstVisibleItemIndex == 0 }
    val viewModel: HomepageViewModel = hiltViewModel()
    val vehiclesList = viewModel.vehicleList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        viewModel.load()
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7f)) {
                DrawerSheet(navController = navController)
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ""
                            )
                        }
                    },
                    title = {
                        if (isCall) {
                            SearchTextField(
                                value = tf.value,
                                onValueChange = {
                                    tf.value = it
                                    viewModel.search(it)
                                },
                                label = { Text(text = stringResource(id = R.string.search)) },
                            )
                        } else {
                            Text(text = stringResource(id = R.string.valet_service))
                        }
                    },
                    actions = {
                        if (isCall) {
                            IconButton(onClick = {
                                isCall = false
                                tf.value = ""
                            }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_image),
                                    contentDescription = "", tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isCall = true
                            }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search_image),
                                    contentDescription = "", tint = Color.Black
                                )
                            }
                        }
                        IconButton(onClick = { navController.navigate(Screen.HourlyFeePage.route) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_feed_24),
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                CustomFabBtn(modifier = Modifier
                    .padding(all = 5.dp),
                    isVisibleBecauseOfScrolling = fabVisibility,
                    onClick = { navController.navigate(Screen.VehicleRegisterPage.route) }
                )
            }

        ) { contentPadding ->
            VehicleListContent(
                vehiclesList = vehiclesList,
                contentPadding = contentPadding,
                onItemClick = { vehicle ->
                    val vehicleJson = Gson().toJson(vehicle)
                    navController.navigate(Screen.VehiclePage.route + "/${vehicleJson}")
                },
                onEditClick = { vehicle ->
                    val vehicleJson = Gson().toJson(vehicle)
                    navController.navigate(Screen.VehicleUpdatePage.route + "/${vehicleJson}")
                }
            )
        }
    }
}




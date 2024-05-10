package com.enons.vehicleapp.presentation.screens.view
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CustomFabButton
import com.enons.vehicleapp.presentation.components.SearchTextField
import com.enons.vehicleapp.presentation.screens.viewmodel.CategoryViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPage(navController: NavController) {
    val tf = remember { mutableStateOf("") }
    var isCall by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyGridState()
    val fabVisibility by derivedStateOf { listState.firstVisibleItemIndex == 0 }
    val viewModel: CategoryViewModel = hiltViewModel()
    val vehiclesList = viewModel.vehicleList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        viewModel.load()
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7f)) {
                DrawerSheet()
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
                                tf.value = "" }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_image),
                                    contentDescription = "", tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isCall = true }
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
                CustomFabButton(modifier = Modifier
                    .padding(all = 5.dp),
                    isVisibleBecauseOfScrolling = fabVisibility,
                    onClick = { navController.navigate(Screen.VehicleRegisterPage.route) }
                )
            }

        ) { it ->
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it),
                columns = GridCells.Fixed(2),
                state = listState,
                contentPadding = PaddingValues(8.dp),
                content = {
                    items(
                        count = vehiclesList.value!!.count(),
                        itemContent = {
                            val vehicle = vehiclesList.value!![it]
                            Card(
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                modifier = Modifier
                                    .padding(all = 5.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(id = R.color.color_3),
                                )
                            ) {
                                Column(modifier = Modifier.clickable {
                                    val vehicleJson = Gson().toJson(vehicle)
                                    navController.navigate(Screen.VehiclePage.route+"/${vehicleJson}")
                                }) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        IconButton(
                                            modifier = Modifier
                                                .align(Alignment.End)
                                                .size(48.dp),
                                            onClick = {
                                                val vehicleJson = Gson().toJson(vehicle)
                                                navController.navigate(Screen.VehicleUpdatePage.route+"/${vehicleJson}")
                                            },

                                            ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_edit_24),
                                                contentDescription = "Localized description",
                                                tint = Color.LightGray
                                            )
                                        }
                                        Image(
                                            modifier = Modifier
                                                .shadow(15.dp, shape = CircleShape),
                                            painter = painterResource(id = R.drawable.default_image),
                                            contentDescription = "",
                                        )
                                        Spacer(modifier = Modifier.size(20.dp))
                                        Text(
                                            text = vehicle.vehicle_number_plate,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W300,
                                            color = colorResource(id = R.color.black)
                                        )
                                        Spacer(modifier = Modifier.size(15.dp))

                                    }
                                }
                            }
                        }
                    )
                }
            )
        }
    }
}



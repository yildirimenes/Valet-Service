package com.yildirim.vehicleapp.view
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.yildirim.vehicleapp.R
import com.yildirim.vehicleapp.components.DeleteAlertDialog
import com.yildirim.vehicleapp.components.DrawerSheet
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.viewmodel.CategoryViewModel
import com.yildirim.vehicleapp.viewmodelfactory.CategoryViewModelFactory
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPage(navController: NavController){
    val isCall = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }
    val defaultController = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isDeleteDialogVisible by remember { mutableStateOf(false) }
    var vehicleToDelete: Vehicles? by remember { mutableStateOf(null) }
    val viewModel : CategoryViewModel = viewModel(
        factory = CategoryViewModelFactory(context.applicationContext as Application)
    )
    val vehiclesList = viewModel.vehicleList.observeAsState(listOf())

    LaunchedEffect(key1 = true){
        viewModel.load()
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
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
                                } }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ""
                            )
                        }
                    },
                    title = {
                        if (isCall.value) {
                            TextField(
                                value = tf.value,
                                onValueChange = {
                                    tf.value = it
                                    viewModel.search(it)
                                },
                                label = { Text(text = stringResource(id = R.string.search)) },
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White,
                                    textColor = Color.Black
                                )
                            )
                        } else {
                            Text(text = stringResource(id = R.string.valet_service))
                        }
                    },
                    actions = {
                        if (isCall.value) {
                            IconButton(onClick = {
                                isCall.value = false
                                tf.value = ""
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_image),
                                    contentDescription = "", tint = Color.Black
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                isCall.value = true
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search_image),
                                    contentDescription = "", tint = Color.Black
                                )
                            }
                        }
                    },
                )
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("vehicle_register_page") },
                    containerColor = colorResource(id = R.color.teal_200),
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "", tint = Color.White
                        )
                    }
                )
            }

        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 70.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                content = {
                    items(
                        count = vehiclesList.value!!.count(),
                        itemContent = {
                            val vehicle = vehiclesList.value!![it]
                            Card(modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()) {
                                Column(modifier = Modifier.clickable {
                                    val vehicleJson = Gson().toJson(vehicle)
                                    navController.navigate("vehicle_page/${vehicleJson}")
                                }) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Spacer(modifier = Modifier.size(15.dp))
                                        Text(text = vehicle.vehicle_number_plate)
                                        Spacer(modifier = Modifier.size(15.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.default_car_image),
                                            contentDescription = "",
                                         )
                                        Row(
                                            modifier = Modifier
                                                .padding(all = 8.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceEvenly

                                        ) {
                                            TextButton(onClick = {
                                                val vehicleJson = Gson().toJson(vehicle)
                                                navController.navigate("vehicle_update_page/${vehicleJson}")
                                            }) {
                                                Text(text = stringResource(id = R.string.update))
                                            }

                                            DeleteAlertDialog(
                                                isDeleteDialogVisible = isDeleteDialogVisible,
                                                onDismiss = {
                                                    isDeleteDialogVisible = false
                                                    vehicleToDelete = null
                                                },
                                                onConfirm = {
                                                    vehicleToDelete?.vehicle_id?.let { id ->
                                                        viewModel.delete(id)
                                                    }
                                                    isDeleteDialogVisible = false
                                                    defaultController.value = true
                                                }
                                            )
                                            TextButton(
                                                onClick = {
                                                    vehicleToDelete = vehicle
                                                    isDeleteDialogVisible = true
                                                }
                                            ) {
                                                Text(text = stringResource(id = R.string.delete))
                                            }

                                        }
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



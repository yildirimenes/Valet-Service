package com.enons.vehicleapp.presentation.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R

@Composable
fun CarNameDropdown(onSelectionChanged: (String, String) -> Unit) {

    var brandDropDownControl by remember { mutableStateOf(false) }
    var modelDropDownControl by remember { mutableStateOf(false) }
    var selectedBrandIndex by remember { mutableIntStateOf(0) }
    var selectedModelIndex by remember { mutableIntStateOf(0) }

    val carBrands = listOf(
        "Marka",
        "Alfa Romeo",
        "Audi",
        "BMW",
        "Chery",
        "Chevrolet",
        "Citroen",
        "Cupra",
        "Dacia",
        "DS",
        "Fiat",
        "Ford",
        "Geely",
        "Honda",
        "Hyundai",
        "Jeep",
        "Kia",
        "Land Rover",
        "Lexus",
        "Mazda",
        "Mercedes",
        "MG",
        "Mini",
        "Mitsubishi",
        "Nissan",
        "Opel",
        "Peugeot",
        "Renault",
        "Seat",
        "Skoda",
        "Ssangyong",
        "Subaru",
        "Suzuki",
        "Tata",
        "Tesla",
        "Tofaş",
        "Togg",
        "Toyota",
        "Volkswagen",
        "Volvo"
    )

    // Define car models for each brand
    val carModels = listOf(
        listOf("Model"),
        listOf("Giulietta", "Tonale"),
        listOf("A1", "A3", "A4", "A5", "A6", "Q2", "Q3"),
        listOf("1 Serisi", "2 Serisi", "3 Serisi", "4 Serisi", "5 Serisi", "X1", "X3", "X5"),
        listOf("Omoda5", "Tiggo7", "Tiggo8", "Alia", "Kimo", "Tiggo"),
        listOf("Aveo", "Cruze", "Captiva", "Lacetti", "Trax", "Camaro"),
        listOf(
            "C3",
            "C3 Aircross",
            "C4",
            "C4 X",
            "C5 Aircross",
            "C-Elysee",
            "Berlingo",
            "C5",
            "C1",
            "C3 Picasso",
            "C4 Cactus",
            "C4 Picasso",
            "Xsara",
            "Nemo"
        ),
        listOf("Formentor"),
        listOf("Duster", "Sandero", "Jogger", "Dokker", "Lodgy", "Logan MCV", "Logan"),
        listOf("DS 4", "DS 7"),
        listOf(
            "Egea",
            "Panda",
            "500X",
            "Fiorino",
            "Doblo Combi",
            "Doblo Cargo",
            "Linea",
            "500L",
            "Albea",
            "Brava",
            "Bravo",
            "Doblo",
            "Freemont",
            "Marea",
            "Palio",
            "Punto",
            "Siena",
            "Stilo",
            "Tempra",
            "Tipo",
            "Uno"
        ),
        listOf(
            "Fiesta",
            "Focus",
            "Puma",
            "Kuga",
            "Transit Courier",
            "Tourneo Courier",
            "Tourneo Connect",
            "Ranger",
            "B-Max",
            "C-Max",
            "EcoSport",
            "Escort",
            "Fusion",
            "Ka",
            "Mondeo",
            "Taunus",
            "Transit Connect"
        ),
        listOf("Emgrand", "Familia", "FC"),
        listOf("Civic", "City", "Accord", "Jazz", "HR-V", "CR-V", "CR-Z"),
        listOf(
            "i10",
            "i20",
            "Bayon",
            "Kona",
            "Elantra",
            "Tucson",
            "Accent",
            "Accent Blue",
            "Accent Era",
            "Excel",
            "Getz",
            "i30",
            "ix35"
        ),
        listOf("Cherokee", "Compass", "Grand Cherokee", "Renegade"),
        listOf(
            "Picanto",
            "Rio",
            "Stonic",
            "Cerato",
            "Ceed",
            "XCeed",
            "Sportage",
            "Sorento",
            "Pride",
            "Venga"
        ),
        listOf("Range Rover"),
        listOf("ES"),
        listOf("CX-3", "CX-5", "RX", "3", "6", "626", "B Serisi"),
        listOf(
            "A Serisi",
            "B Serisi",
            "C Serisi",
            "CLA Serisi",
            "CLS",
            "E Serisi",
            "GLA Serisi",
            "Vito"
        ),
        listOf("HS", "MG4", "ZS"),
        listOf("Cooper"),
        listOf("Space Star", "ASX", "L 200", "Lancer", "Attrage", "Colt"),
        listOf(
            "Juke",
            "Qashqai",
            "X-Trail",
            "Micra",
            "Navara",
            "Note",
            "Primera",
            "Pulsar",
            "Skystar"
        ),
        listOf(
            "Corsa",
            "Astra",
            "Mokka",
            "Crossland",
            "Grandland",
            "Combo",
            "Zafira",
            "Insignia",
            "Meriva",
            "Vectra",
            "Tigra"
        ),
        listOf(
            "208",
            "308",
            "2008",
            "408",
            "3008",
            "508",
            "5008",
            "Rifter",
            "Partner",
            "Bipper",
            "106",
            "107",
            "206",
            "207",
            "301",
            "306",
            "307",
            "407"
        ),
        listOf(
            "Clio",
            "Taliant",
            "Captur",
            "Megane",
            "Austral",
            "Koleos",
            "Kangoo",
            "Kadjar",
            "Fluence",
            "Laguna",
            "Latitude",
            "Symbol",
            "Talisman",
            "Scenic",
            "R 11",
            "R 19",
            "R 21",
            "R 9"
        ),
        listOf("Ibiza", "Leon", "Arona", "Ateca", "Altea", "Cordoba", "Toledo"),
        listOf(
            "Fabia",
            "Scala",
            "Octavia",
            "Superb",
            "Kamiq",
            "Karoq",
            "Kodiaq",
            "Citigo",
            "Rapid",
            "Roomster",
            "Yeti"
        ),
        listOf("Torres"),
        listOf("Forester", "XV", "Impreza"),
        listOf("Swift", "S-Cross", "Vitara", "SX4", "Jimny", "Baleno", "Splash", "Alto"),
        listOf("Vista", "Indica", "Xenon", "Telcoline"),
        listOf("Model Y"),
        listOf("Şahin", "Doğan", "Kartal", "Murat"),
        listOf("T10X"),
        listOf(
            "Corolla",
            "Yaris",
            "C-HR",
            "RAV4",
            "Proace City",
            "Hilux",
            "Auris",
            "Avensis",
            "Verso"
        ),
        listOf(
            "Polo",
            "Golf",
            "T-Cross",
            "Taigo",
            "T-Roc",
            "Tiguan",
            "Caddy",
            "Amarok",
            "Transporter",
            "Passat",
            "Jetta",
            "Scirocco",
            "CC",
            "Bora"
        ),
        listOf("V40", "XC40", "XC60", "XC90", "S40", "S60", "S80", "S90")
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Brand Dropdown
        OutlinedCard(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(125.dp)
                    .height(50.dp)
                    .padding(5.dp)
                    .clickable {
                        brandDropDownControl = true
                    }
            ) {
                Text(text = carBrands[selectedBrandIndex])
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = ""
                )
            }

            DropdownMenu(
                expanded = brandDropDownControl,
                onDismissRequest = { brandDropDownControl = false },
                modifier = Modifier.requiredSizeIn(maxHeight = 300.dp)
            ) {
                carBrands.forEachIndexed { index, brand ->
                    DropdownMenuItem(
                        text = { Text(text = brand) },
                        onClick = {
                            brandDropDownControl = false
                            selectedBrandIndex = index
                            // Reset model index when brand changes
                            selectedModelIndex = 0
                            onSelectionChanged(
                                carBrands[selectedBrandIndex],
                                carModels[selectedBrandIndex][selectedModelIndex]
                            )
                        }
                    )
                }
            }
        }

        // Model Dropdown
        OutlinedCard(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(125.dp)
                    .height(50.dp)
                    .padding(5.dp)
                    .clickable {
                        modelDropDownControl = true
                    }
            ) {
                Text(text = carModels[selectedBrandIndex][selectedModelIndex])
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = ""
                )
            }

            DropdownMenu(
                expanded = modelDropDownControl,
                onDismissRequest = { modelDropDownControl = false },
                modifier = Modifier.requiredSizeIn(maxHeight = 300.dp)
            ) {
                carModels[selectedBrandIndex].forEachIndexed { index, model ->
                    DropdownMenuItem(
                        text = { Text(text = model) },
                        onClick = {
                            modelDropDownControl = false
                            selectedModelIndex = index
                            onSelectionChanged(
                                carBrands[selectedBrandIndex],
                                carModels[selectedBrandIndex][selectedModelIndex]
                            )
                        }
                    )
                }
            }
        }
    }
}

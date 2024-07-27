package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enons.vehicleapp.R

@Composable
fun CustomBtn(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_green))
    ) {
        Text(text = text)
    }
}

@Composable
fun CallBtn(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_green))
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp),
            )
            Text(text = stringResource(id = R.string.call), fontSize = 16.sp)
        }
    }
}

@Composable
fun MessageBtn(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_green))
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp),
            )
            Text(text = stringResource(id = R.string.notification), fontSize = 16.sp)
        }
    }
}


@Composable
fun DeleteBtn(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color,

    ) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)

    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Composable
fun OnBoardingBtn(
    text: String = "Next",
    containerColor: Color,
    contentColor: Color,

    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(10.dp),
    ) {

        Text(text = text, fontSize = fontSize.sp, style = textStyle)
    }
}
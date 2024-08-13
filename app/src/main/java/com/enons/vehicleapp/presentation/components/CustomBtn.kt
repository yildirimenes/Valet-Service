package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enons.vehicleapp.R

@Composable
fun CustomBtn(
    onClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun CallBtn(
    onClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    imageVector: ImageVector = Icons.Default.Call
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Row {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp),
            )
            Text(text = text, fontSize = fontSize.sp)
        }
    }
}

@Composable
fun MessageBtn(
    onClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    imageVector: ImageVector = Icons.Default.MailOutline
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Row {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp),
            )
            Text(text = text, fontSize = fontSize.sp)
        }
    }
}


@Composable
fun DeleteBtn(
    onClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    fontSize: Int = 18,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = elevation
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}

@Composable
fun OnBoardingBtn(
    onClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape,
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle
        )
    }
}

@Composable
fun AuthBtn(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = colorResource(id = R.color.dark_green),
    contentColor: Color = colorResource(id = R.color.color_3),
    padding: Dp = 8.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = text, Modifier.padding(vertical = padding))
    }
}

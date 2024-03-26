package com.example.dualingo_clone.onboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.dualingo_clone.ui.theme.AppTheme


@Composable
fun ImageComponent(imageResId: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ThreeDotsComponent(
    dotSize: Dp = 8.dp,
    dotSpacing: Dp = 8.dp,
    modifier:Modifier,

    dotIndexInColorA: Int
) {
    val activeColor = AppTheme.colors.activeDot
    val nonActiveColor = AppTheme.colors.userListItem
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 3) activeColor else nonActiveColor, CircleShape)
        )
        Box(modifier = Modifier.width(dotSpacing))
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 2) activeColor else nonActiveColor, CircleShape)
        )
        Box(modifier = Modifier.width(dotSpacing))
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(if (dotIndexInColorA == 1) activeColor else nonActiveColor, CircleShape)
        )
    }
}

@Composable
fun BoldText(text: String, modifier: Modifier) {
    BasicText(
        text = text,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colors.boldText
        ),
        modifier = modifier,
    )
}

@Composable
fun TextComponent(text: String, modifier: Modifier) {
    BasicText(
        text = text,
        style = TextStyle(
            fontSize = 15.sp,
            color = AppTheme.colors.text,
            textAlign = TextAlign.Center
        ),
        modifier = modifier,
    )
}

@Composable
fun LabelWithLinkComponent(
    labelText: String,
    linkText: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Row(modifier = modifier) {
        Text(
            text = labelText,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.text,
            ),
        )
        Text(
            text = linkText,
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.button,
            ),
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}
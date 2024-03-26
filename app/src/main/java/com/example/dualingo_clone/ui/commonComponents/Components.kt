package com.example.dualingo_clone.ui.commonComponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun ButtonComponent(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12),
        colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.button),
        modifier = modifier
    ) {
        Text(
            text = text,
            style= TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        )
    }
}
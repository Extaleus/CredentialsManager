package com.extaleusinc.credentialsmanager.ui.widgets

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(thick: Dp = 2.dp) {
    HorizontalDivider(Modifier.fillMaxWidth(), thickness = thick, color = Color.White.copy(alpha = 0.1f))
}

@Composable
fun VerticalDivider(thick: Dp = 2.dp) {
    VerticalDivider(Modifier.fillMaxHeight(), thickness = thick, color = Color.White.copy(alpha = 0.1f))
}


package com.extaleusinc.credentialsmanager.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.extaleusinc.credentialsmanager.DIVIDER_COLOR

@Composable
fun HorizontalDivider(thick: Dp = 4.dp) {
    HorizontalDivider(Modifier.fillMaxWidth(), thickness = thick, color = DIVIDER_COLOR)
}
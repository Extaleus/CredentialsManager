package com.extaleusinc.credentialsmanager.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.extaleusinc.credentialsmanager.colors

@Composable
fun Folder(folderName: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = colors,
                    tileMode = TileMode.Clamp,
                    startX = 0f,
                    endX = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() / 2 }
                )
            )
    ) {
        Text(
            text = folderName,
            Modifier
                .padding(10.dp)
        )
        HorizontalDivider(2.dp)
    }
}
package com.extaleusinc.credentialsmanager.ui.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.R
import com.extaleusinc.credentialsmanager.feature.home.HomeAction
import com.extaleusinc.data.model.FolderModel

@Composable
fun Folder(folder: FolderModel, navController: NavController, onAction: (HomeAction) -> Unit) {
    var folderOpen by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
        /*            .background(
                        Brush.horizontalGradient(
                            colors = colors,
                            tileMode = TileMode.Clamp,
                            startX = 0f,
                            endX = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() / 2 }
                        )
                    )*/
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    Log.d("my", "click to folder: ${folder.folderName}")
                    folderOpen = folder.entities.isNotEmpty() && !folderOpen
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (folder.entities.isNotEmpty()) {
                Image(
                    painter = if (folderOpen) painterResource(id = R.drawable.keyboard_arrow_down_24dp)
                    else painterResource(id = R.drawable.keyboard_arrow_right_24dp),
                    contentDescription = null,
                )
            }
            Text(
                text = folder.folderName,
                Modifier
                    .padding(10.dp)
            )
        }
        if (folderOpen) {
            folder.entities.forEach { entity ->
                HorizontalDivider(2.dp)
                Entity(entity, navController, onAction)
            }
        }
    }
}
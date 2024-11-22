package com.extaleusinc.credentialsmanager.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.feature.home.HomeAction
import com.extaleusinc.data.model.EntitiesModel
import com.extaleusinc.data.model.FolderModel

@Composable
fun Folder(
    folder: Pair<FolderModel, EntitiesModel>,
    navController: NavController,
    onAction: (HomeAction) -> Unit
) {
//    var folderOpen by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Black),
//                .clickable {
//                    Log.d("my", "click to folder: ${folder.title}")
//                    folderOpen = folder.entities.isNotEmpty() && !folderOpen
//                },
            verticalAlignment = Alignment.CenterVertically
        ) {
//            if (folder.entities.isNotEmpty()) {
//                Image(
//                    painter = if (folderOpen) painterResource(id = R.drawable.keyboard_arrow_down_24dp)
//                    else painterResource(id = R.drawable.keyboard_arrow_right_24dp),
//                    contentDescription = null,
//                )
//            }
            Text(
                text = folder.first.title,
                Modifier
                    .padding(10.dp)
            )
            Spacer(Modifier.weight(1f))
            CircularProgressIndicator(modifier = Modifier.scale(0.8f))
//        }
//        if (folderOpen) {
//            folder.entities.forEach { entity ->
//                HorizontalDivider(2.dp)
//                Entity(entity, navController, onAction)
//            }
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            folder.second.entities.forEach { entity ->
                Text(text = entity.title)
            }
        }
    }
}
package com.extaleusinc.credentialsmanager.ui.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.MAIN_COLOR_BEBE
import com.extaleusinc.credentialsmanager.R
import com.extaleusinc.credentialsmanager.feature.home.FolderWithEntities
import com.extaleusinc.credentialsmanager.feature.home.HomeAction
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.model.FolderModel

@Composable
fun Folder(
    folderWithEntities: FolderWithEntities,
    navController: NavController,
    onAction: (HomeAction) -> Unit
) {
    var folderOpen by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MAIN_COLOR_BEBE)
                .clickable {
                    Log.d("my", "click to folder: ${folderWithEntities.folder.title}")
                    folderOpen = folderWithEntities.entities.isNotEmpty() && !folderOpen
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = folderWithEntities.folder.title,
                Modifier
                    .padding(10.dp)
            )
            Spacer(Modifier.weight(1f))
            if (!folderWithEntities.folder.isLoading) {
                if (folderWithEntities.entities.isNotEmpty()) {
                    Image(
                        painter = if (folderOpen) painterResource(id = R.drawable.keyboard_arrow_down_24dp)
                        else painterResource(id = R.drawable.keyboard_arrow_right_24dp),
                        contentDescription = null,
                    )
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.scale(0.6f))
            }
        }
        if (folderOpen) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                folderWithEntities.entities.forEach { entity ->
                    Entity(entity, navController, onAction)
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun FolderPreview() {
    Folder(
        folderWithEntities = FolderWithEntities(
            folder = FolderModel(
                id = 1,
                title = "Work",
                description = "Work Folder",
                isLoading = true
            ),
            entities = listOf(
                EntityModel(
                    0,
                    "title",
                    "username",
                    "password",
                    "url",
                    "description",
                    favorite = false
                )
            )
        ),
        navController = NavController(LocalContext.current),
    ) {}
}
package com.extaleusinc.credentialsmanager.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.MAIN_COLOR
import com.extaleusinc.credentialsmanager.TEXT_COLOR
import com.extaleusinc.credentialsmanager.TOP_BAR_COLOR
import com.extaleusinc.credentialsmanager.ui.widgets.Folder
import com.extaleusinc.credentialsmanager.ui.widgets.HorizontalDivider

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Home(navController = navController, state = state, onAction = viewModel::processAction)
}

@Composable
fun Home(
    navController: NavController,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = TOP_BAR_COLOR)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Folders",
                style = TextStyle(fontSize = 24.sp, color = TEXT_COLOR)
            )
        }

        HorizontalDivider()

        if (state.foldersTree != null) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MAIN_COLOR)
            ) {
                items(state.foldersTree.folders) { folder ->
                    Folder(folder, navController, onAction)
                }
            }
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MAIN_COLOR)
            )
        }

        HorizontalDivider()

        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = TOP_BAR_COLOR),
        ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.Center)) {
                Text(text = "Add New Folder")
            }
        }
    }
}
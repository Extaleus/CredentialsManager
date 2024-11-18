package com.extaleusinc.credentialsmanager.feature.entityDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun EntityDetailsScreen(
    navController: NavController,
    viewModel: EntityDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {
        Text(text = state.entity?.entityName ?: "")
        Text(text = state.entity?.username ?: "")
        Text(text = state.entity?.password ?: "")
        Text(text = state.entity?.url ?: "")
        Text(text = state.entity?.notes ?: "")

        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(text = "go back")
        }
    }
}
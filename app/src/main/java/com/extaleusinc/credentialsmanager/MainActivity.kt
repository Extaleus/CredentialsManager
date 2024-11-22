package com.extaleusinc.credentialsmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.extaleusinc.credentialsmanager.feature.auth.Auth
import com.extaleusinc.credentialsmanager.feature.entityDetails.EntityDetailsScreen
import com.extaleusinc.credentialsmanager.feature.entityDetails.EntityDetailsViewModel
import com.extaleusinc.credentialsmanager.feature.home.Home
import com.extaleusinc.credentialsmanager.ui.theme.CredentialsManagerTheme
import com.extaleusinc.data.model.EntityModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CredentialsManagerTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Auth
                    ) {
                        composable<Auth> {
                            Auth(navController = navController)
                        }
                        composable<Home> {
                            Home(navController = navController)
                        }
                        composable<EntityDetails> {
                            val args = it.toRoute<EntityDetails>()
                            EntityDetailsScreen(
                                navController = navController,
                                viewModel = hiltViewModel<EntityDetailsViewModel, EntityDetailsViewModel.Factory>(
                                    creationCallback = { factory ->
                                        factory.create(
                                            EntityModel(
                                                args.id,
                                                args.title,
                                                args.username,
                                                args.password,
                                                args.url,
                                                args.description,
                                                args.favorite
                                            )
                                        )
                                    }
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}

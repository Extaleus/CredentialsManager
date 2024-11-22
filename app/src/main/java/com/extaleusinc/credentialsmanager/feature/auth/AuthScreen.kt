package com.extaleusinc.credentialsmanager.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.extaleusinc.credentialsmanager.Auth
import com.extaleusinc.credentialsmanager.Home
import de.palm.composestateevents.NavigationEventEffect

@Composable
fun Auth(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Auth(
        navController = navController,
        state = state,
        onAction = viewModel::processAction
    )
}

@Composable
fun Auth(
    navController: NavController,
    state: AuthState,
    onAction: (AuthAction) -> Unit
) {
    NavigationEventEffect(
        event = state.authSuccess,
        onConsumed = { onAction(AuthAction.ConsumeError) }) {
        navController.navigate(Home) {
            popUpTo<Auth> {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = if (state.isAuthScreen) "Authorization" else "Registration",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 32.sp),
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (!state.isAuthScreen) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.screenInfo.name,
                    onValueChange = {
                        onAction(
                            AuthAction.ChangeScreenInfo(
                                state.screenInfo.copy(
                                    name = it
                                )
                            )
                        )
                    },
                    placeholder = { Text("Name") }
                )
            }
            TextField(
                modifier = if (state.isAuthScreen) {
                    Modifier
                        .fillMaxWidth()
                } else {
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                },
                value = state.screenInfo.username,
                onValueChange = {
                    onAction(
                        AuthAction.ChangeScreenInfo(
                            state.screenInfo.copy(
                                username = it
                            )
                        )
                    )
                },
                placeholder = { Text("Username") }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = state.screenInfo.password,
                onValueChange = {
                    onAction(
                        AuthAction.ChangeScreenInfo(
                            state.screenInfo.copy(
                                password = it
                            )
                        )
                    )
                },
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.canChangeScreen) {
                TextButton(onClick = { onAction(AuthAction.ChangeAuthScreen) }) {
                    Text(text = if (state.isAuthScreen) "Sign Up" else "Sign In")
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    if (state.isAuthScreen) {
                        onAction(AuthAction.SignIn)
                    } else {
                        onAction(AuthAction.SignUp)
                    }
                }) {
                Text(text = if (state.isAuthScreen) "Sign In" else "Sign Up")
            }
        }
    }
}

@Preview
@Composable
fun AuthPreview() {
    Auth(
        navController = NavController(LocalContext.current),
        state = AuthState(),
        onAction = {})
}
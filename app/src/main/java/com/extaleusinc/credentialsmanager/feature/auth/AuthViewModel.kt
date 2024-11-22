package com.extaleusinc.credentialsmanager.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extaleusinc.data.model.FoldersModel
import com.extaleusinc.data.network.api.request.SignInRequest
import com.extaleusinc.data.network.api.request.SignUpRequest
import com.extaleusinc.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<AuthState> =
        MutableStateFlow(AuthState.initial())
    val state: StateFlow<AuthState> = _state

    private fun signIn() {
        viewModelScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
            repository.signIp(
                SignInRequest(
                    state.value.screenInfo.username,
                    state.value.screenInfo.password
                )
            ).onSuccess {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            authSuccess = triggered
                        )
                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
            repository.signUp(
                SignUpRequest(
                    state.value.screenInfo.name,
                    state.value.screenInfo.username,
                    state.value.screenInfo.password
                )
            ).onSuccess {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(isAuthScreen = true, canChangeScreen = false, isLoading = false)
                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    fun processAction(action: AuthAction) {
        when (action) {
            AuthAction.ConsumeError -> {
                _state.update {
                    it.copy(authSuccess = consumed)
                }
            }

            AuthAction.SignIn -> {
                signIn()
            }

            AuthAction.SignUp -> {
                signUp()
            }

            AuthAction.ChangeAuthScreen -> {
                _state.update {
                    it.copy(isAuthScreen = !state.value.isAuthScreen)
                }
            }

            is AuthAction.ChangeScreenInfo -> {
                _state.update {
                    it.copy(screenInfo = action.screenInfo)
                }
            }
        }
    }
}

data class AuthState(
    val isAuthScreen: Boolean = true,
    val canChangeScreen: Boolean = true,
    val isLoading: Boolean = false,
    val screenInfo: ScreenInfo = ScreenInfo("", "", ""),
    val foldersTree: FoldersModel? = null,
    val authSuccess: StateEvent = consumed,
) {
    companion object {
        fun initial() = AuthState()
    }
}

data class ScreenInfo(
    val name: String,
    val username: String,
    val password: String,
)

sealed class AuthAction {
    data object SignUp : AuthAction()
    data object SignIn : AuthAction()

    data class ChangeScreenInfo(
        val screenInfo: ScreenInfo
    ) : AuthAction()

    data object ChangeAuthScreen : AuthAction()
    data object ConsumeError : AuthAction()
}
package com.extaleusinc.credentialsmanager.home

import androidx.lifecycle.ViewModel
import com.extaleusinc.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.initial())
    val state: StateFlow<HomeState> = _state


}

data class HomeState(
    val isLoading: Boolean = false,
    val hello: String = "hello"
) {
    companion object {
        fun initial() = HomeState()
    }
}
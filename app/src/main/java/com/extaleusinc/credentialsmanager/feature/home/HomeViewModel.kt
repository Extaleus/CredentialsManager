package com.extaleusinc.credentialsmanager.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.model.FoldersModel
import com.extaleusinc.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.initial())
    val state: StateFlow<HomeState> = _state

    init {
        getFolders()
    }

    fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.DeleteEntity -> {
                Log.d("my", "call to entity delete")
            }

            HomeAction.ConsumeError -> TODO()
        }
    }

    private fun getFolders() {
        repository.getFolders()
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        foldersTree = result
                    )
                }
            }
    }

}

data class HomeState(
    val isLoading: Boolean = false,
    val foldersTree: FoldersModel? = null
) {
    companion object {
        fun initial() = HomeState()
    }
}

sealed class HomeAction {
    data class DeleteEntity(
        val entity: EntityModel
    ) : HomeAction()

    data object ConsumeError : HomeAction()
}
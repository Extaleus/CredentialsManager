package com.extaleusinc.credentialsmanager.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extaleusinc.data.model.EntitiesModel
import com.extaleusinc.data.model.FolderModel
import com.extaleusinc.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
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

    private fun getFolders() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
            repository.getAllFolders().onSuccess { result ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            folders = result.folders
                        )
                    }
                    if (state.value.folders.isNotEmpty()) {
                        getEntitiesByFolder()
                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
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

    private fun getEntitiesByFolder() {
        val foldersWithEntitiesMutex = Mutex()

        val getEntitiesByFolderJob = viewModelScope.launch(Dispatchers.IO) {
            state.value.folders.forEach { folder ->
                repository.getEntitiesByFolder(folder.id).onSuccess { result ->
                    foldersWithEntitiesMutex.withLock {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    foldersWithEntities = it.foldersWithEntities.toMutableList()
                                        .also { list ->
                                            list.add(Pair(folder, result))
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }

        viewModelScope.launch {
            getEntitiesByFolderJob.join()
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun processAction(action: HomeAction) {
        when (action) {
            HomeAction.ConsumeError -> TODO()
        }
    }

}


data class HomeState(
    val isLoading: Boolean = false,
    val folders: List<FolderModel> = emptyList(),
    val foldersWithEntities: MutableList<Pair<FolderModel, EntitiesModel>> = emptyList<Pair<FolderModel, EntitiesModel>>().toMutableList(),
) {
    companion object {
        fun initial() = HomeState()
    }
}

sealed class HomeAction {
    data object ConsumeError : HomeAction()
}
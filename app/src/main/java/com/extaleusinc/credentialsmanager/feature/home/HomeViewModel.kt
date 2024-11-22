package com.extaleusinc.credentialsmanager.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extaleusinc.data.model.EntityModel
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
                    if (result.folders.isNullOrEmpty()) {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    } else {
                        getEntitiesByEachFolder(result.folders!!)
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

    private fun getEntitiesByEachFolder(folders: List<FolderModel>) {
        _state.update {
            it.copy(
                foldersWithEntities = folders.map { folder ->
                    FolderWithEntities(folder.copy(isLoading = true), emptyList())
                }.toMutableList(),
            )
        }

        val foldersWithEntitiesMutex = Mutex()

        val getEntitiesByFolderJob = viewModelScope.launch(Dispatchers.IO) {
            state.value.foldersWithEntities.forEachIndexed { index, folder ->
                repository.getEntitiesByEachFolder(folder.folder.id).onSuccess { result ->
                    foldersWithEntitiesMutex.withLock {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    foldersWithEntities = it.foldersWithEntities.map { currentFolderWithEntities ->
                                        if (currentFolderWithEntities.folder.id == folder.folder.id) {
                                            currentFolderWithEntities.copy(
                                                folder = currentFolderWithEntities.folder.copy(
                                                    isLoading = false,
                                                    selected = index == 0
                                                ),
                                                entities = result.entities ?: emptyList()
                                            )
                                        } else {
                                            currentFolderWithEntities
                                        }
                                    }.toMutableList()
                                )
                            }
                        }
                    }
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
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

            is HomeAction.ChangeSelectedFolder -> {
                _state.update {
                    it.copy(
                        foldersWithEntities = it.foldersWithEntities.map { currentFolderWithEntities ->
                            if (currentFolderWithEntities.folder == action.folder) {
                                currentFolderWithEntities.copy(
                                    folder = currentFolderWithEntities.folder.copy(
                                        selected = true
                                    ),
                                    entities = currentFolderWithEntities.entities
                                )
                            } else {
                                currentFolderWithEntities.copy(
                                    folder = currentFolderWithEntities.folder.copy(
                                        selected = false
                                    ),
                                    entities = currentFolderWithEntities.entities
                                )
                            }
                        }.toMutableList()
                    )
                }
            }
        }
    }

}

data class FolderWithEntities(
    val folder: FolderModel,
    val entities: List<EntityModel>
)

data class HomeState(
    val isLoading: Boolean = false,
    val foldersWithEntities: MutableList<FolderWithEntities> = emptyList<FolderWithEntities>().toMutableList(),
) {
    companion object {
        fun initial() = HomeState()
    }
}

sealed class HomeAction {
    data object ConsumeError : HomeAction()
    data class ChangeSelectedFolder(val folder: FolderModel) : HomeAction()
}
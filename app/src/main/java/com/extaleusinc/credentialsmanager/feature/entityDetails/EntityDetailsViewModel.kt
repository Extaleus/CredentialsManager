package com.extaleusinc.credentialsmanager.feature.entityDetails

import androidx.lifecycle.ViewModel
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.repository.home.HomeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = EntityDetailsViewModel.Factory::class)
class EntityDetailsViewModel @AssistedInject constructor(
    @Assisted private val entity: EntityModel,
    private val repository: HomeRepository
) : ViewModel() {
    private val _state: MutableStateFlow<EntityDetailsState> =
        MutableStateFlow(EntityDetailsState.initial())
    val state: StateFlow<EntityDetailsState> = _state

    init {
        _state.update {
            it.copy(
                entity = entity
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            entity: EntityModel
        ): EntityDetailsViewModel
    }
}

data class EntityDetailsState(
    val isLoading: Boolean = false,
    val entity: EntityModel? = null
) {
    companion object {
        fun initial() = EntityDetailsState()
    }
}
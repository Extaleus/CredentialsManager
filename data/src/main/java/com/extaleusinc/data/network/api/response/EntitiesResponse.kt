package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.EntitiesModel

data class EntitiesResponse(
    val entities: List<EntityResponse>
) {
    fun toModel() = EntitiesModel(
        entities = entities.map { it.toModel() }
    )
}
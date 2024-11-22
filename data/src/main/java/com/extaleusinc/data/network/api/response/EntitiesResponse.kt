package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.EntitiesModel

data class EntitiesResponse(
    val data: List<EntityResponse>?
) {
    fun toModel() = EntitiesModel(
        entities = data?.map { it.toModel() }
    )
}
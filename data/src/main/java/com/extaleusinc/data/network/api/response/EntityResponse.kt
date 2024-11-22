package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.EntityModel

data class EntityResponse(
    val id: Int,
    val title: String,
    val username: String,
    val password: String,
    val url: String,
    val description: String,
    val done: Boolean,
){
    fun toModel() = EntityModel(
        id = id,
        title = title,
        username = username,
        password = password,
        url = url,
        description = description,
        favorite = done
    )
}

package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.FolderModel

data class FolderResponse(
    val id: Int,
    val title: String,
    val description: String
){
    fun toModel() = FolderModel(
        id = id,
        title = title,
        description = description,
        isLoading = false,
        selected = false,
        icon = ""
    )
}

package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.FoldersModel

data class FoldersResponse(
    val data: List<FolderResponse>?
) {
    fun toModel() = FoldersModel(
        folders = data?.map { it.toModel() }
    )
}

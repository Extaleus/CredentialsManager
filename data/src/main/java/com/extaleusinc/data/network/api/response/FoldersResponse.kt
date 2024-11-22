package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.FolderModel
import com.extaleusinc.data.model.FoldersModel

data class FoldersResponse(
    val data: List<FolderModel>?
) {
    fun toModel() = FoldersModel(
        folders = data
    )
}

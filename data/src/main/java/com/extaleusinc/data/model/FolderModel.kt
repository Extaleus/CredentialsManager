package com.extaleusinc.data.model

data class FoldersModel(
    val folders: List<FolderModel>
)

data class FolderModel(
    val folderName: String,
    val entities: List<EntityModel>
)

data class EntityModel(
    val entityName: String,
    val username: String,
    val password: String,
    val url: String,
    val notes: String
)

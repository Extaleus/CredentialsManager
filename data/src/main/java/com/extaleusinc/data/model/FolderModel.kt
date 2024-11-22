package com.extaleusinc.data.model

data class FolderModel(
    val id: Int,
    val title: String,
    val description: String,
    val isLoading: Boolean = false,
    val selected: Boolean = false,
    val icon: String = "",
)

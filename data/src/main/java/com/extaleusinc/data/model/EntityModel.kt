package com.extaleusinc.data.model

data class EntityModel(
    val id: Int,
    val title: String,
    val username: String,
    val password: String,
    val url: String,
    val description: String,
    val favorite: Boolean
)

package com.extaleusinc.credentialsmanager

import kotlinx.serialization.Serializable

@Serializable
object Auth

@Serializable
object Home

@Serializable
data class EntityDetails(
    val id: Int,
    val title: String,
    val username: String,
    val password: String,
    val url: String,
    val description: String,
    val favorite: Boolean,
)

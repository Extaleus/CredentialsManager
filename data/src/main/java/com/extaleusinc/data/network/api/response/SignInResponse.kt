package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.SignInModel

data class SignInResponse(
    val token: String
) {
    fun toModel() = SignInModel(
        token = token
    )
}
package com.extaleusinc.data.network.api.response

import com.extaleusinc.data.model.SignUpModel

data class SignUpResponse(
    val userId: Int
) {
    fun toModel() = SignUpModel(
        userId = userId
    )
}
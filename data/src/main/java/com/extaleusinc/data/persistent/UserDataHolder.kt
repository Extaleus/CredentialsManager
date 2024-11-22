package com.extaleusinc.data.persistent

internal object UserDataHolder {
    private var token: String? = null

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken(): String? {
        return token
    }
}
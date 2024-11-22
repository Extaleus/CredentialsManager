package com.extaleusinc.data.base

internal object Api {
    //AUTH
    const val API_SIGN_UP = "auth/sign-up"
    const val API_SIGN_IN = "auth/sign-in"

    //FOLDERS
    const val API_CREATE_FOLDER = "api/lists"
    const val API_GET_ALL_FOLDERS = "api/lists"
    const val API_GET_FOLDER_BY_ID = "api/lists/{id}"
    const val API_UPDATE_FOLDER_BY_ID = "api/lists/{id}"
    const val API_DELETE_FOLDER_BY_ID = "api/lists/{id}"

    //ENTITIES

    const val API_GET_ALL_ENTITIES = "api/lists/{id}/items"

}
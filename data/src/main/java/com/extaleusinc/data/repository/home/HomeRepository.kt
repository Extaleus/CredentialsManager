package com.extaleusinc.data.repository.home

import com.extaleusinc.data.model.EntitiesModel
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.model.FoldersModel
import com.extaleusinc.data.model.SignInModel
import com.extaleusinc.data.model.SignUpModel
import com.extaleusinc.data.network.api.request.SignInRequest
import com.extaleusinc.data.network.api.request.SignUpRequest

interface HomeRepository {
    suspend fun signUp(request: SignUpRequest): Result<SignUpModel>

    suspend fun signIp(request: SignInRequest): Result<SignInModel>

    suspend fun getAllFolders(): Result<FoldersModel>

    suspend fun getEntitiesByFolder(folderId: Int): Result<EntitiesModel>
}





package com.extaleusinc.data.repository.home

import android.content.SharedPreferences
import com.extaleusinc.data.model.EntitiesModel
import com.extaleusinc.data.model.FoldersModel
import com.extaleusinc.data.model.SignInModel
import com.extaleusinc.data.model.SignUpModel
import com.extaleusinc.data.network.api.ApiService
import com.extaleusinc.data.network.api.request.SignInRequest
import com.extaleusinc.data.network.api.request.SignUpRequest
import com.extaleusinc.data.persistent.UserDataHolder
import com.extaleusinc.data.persistent.getValue
import com.extaleusinc.data.persistent.setValue
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    preferences: SharedPreferences
) : HomeRepository {
    private var token: String? by preferences

    override suspend fun signUp(request: SignUpRequest): Result<SignUpModel> {
        return apiService.signUp(request).map {
            it.toModel()
        }
    }

    override suspend fun signIp(request: SignInRequest): Result<SignInModel> {
        return apiService.signIn(request).map {
            this.token = it.token
            UserDataHolder.setToken(it.token)
            it.toModel()
        }
    }

    override suspend fun getAllFolders(): Result<FoldersModel> {
        return apiService.getAllFolders().map {
            it.toModel()
        }
    }

    override suspend fun getEntitiesByEachFolder(folderId: Int): Result<EntitiesModel> {
        return apiService.getEntitiesByEachFolder(folderId).map {
            it.toModel()
        }
    }
}
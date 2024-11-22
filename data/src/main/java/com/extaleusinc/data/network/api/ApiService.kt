package com.extaleusinc.data.network.api

import com.extaleusinc.data.base.Api.API_GET_ALL_ENTITIES
import com.extaleusinc.data.base.Api.API_GET_ALL_FOLDERS
import com.extaleusinc.data.base.Api.API_SIGN_IN
import com.extaleusinc.data.base.Api.API_SIGN_UP
import com.extaleusinc.data.network.api.request.SignInRequest
import com.extaleusinc.data.network.api.request.SignUpRequest
import com.extaleusinc.data.network.api.response.EntitiesResponse
import com.extaleusinc.data.network.api.response.FoldersResponse
import com.extaleusinc.data.network.api.response.SignInResponse
import com.extaleusinc.data.network.api.response.SignUpResponse
import com.extaleusinc.data.utils.RequiresToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ApiService {
    //AUTH
    @POST(API_SIGN_UP)
    suspend fun signUp(@Body request: SignUpRequest): Result<SignUpResponse>

    @POST(API_SIGN_IN)
    suspend fun signIn(@Body request: SignInRequest): Result<SignInResponse>

    //FOLDERS
//    @POST(API_CREATE_FOLDER)

    @RequiresToken
    @GET(API_GET_ALL_FOLDERS)
    suspend fun getAllFolders(): Result<FoldersResponse>

//    @GET(API_GET_FOLDER_BY_ID)
//    @PUT(API_UPDATE_FOLDER_BY_ID)
//    @DELETE(API_DELETE_FOLDER_BY_ID)

    //ENTITIES


    @RequiresToken
    @GET(API_GET_ALL_ENTITIES)
    suspend fun getEntitiesByEachFolder(@Path("id") id: Int): Result<EntitiesResponse>


}
package com.extaleusinc.data.network.di

import com.extaleusinc.data.base.Constants
import com.extaleusinc.data.interceptors.AuthInterceptor
import com.extaleusinc.data.network.api.ApiService
import com.extaleusinc.data.network.api.ResultCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    fun getGson(): Gson =
        GsonBuilder().serializeNulls().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        return httpBuilder.protocols(mutableListOf(Protocol.HTTP_1_1)).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient, gson: Gson
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

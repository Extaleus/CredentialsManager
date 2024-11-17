package com.extaleusinc.data.repository.home

import android.content.SharedPreferences
import com.extaleusinc.data.network.api.ApiService
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    preferences: SharedPreferences
) : HomeRepository {

}
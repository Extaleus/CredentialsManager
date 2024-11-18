package com.extaleusinc.data.repository.home

import android.content.SharedPreferences
import com.extaleusinc.data.model.EntityModel
import com.extaleusinc.data.model.FolderModel
import com.extaleusinc.data.model.FoldersModel
import com.extaleusinc.data.network.api.ApiService
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    preferences: SharedPreferences
) : HomeRepository {
    override fun getFolders(): Result<FoldersModel> {
        return Result.success(
            FoldersModel(
                folders = listOf(
                    FolderModel(
                        "gmail", listOf(
                            EntityModel(
                                "gmail account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            )
                        )
                    ),
                    FolderModel(
                        "steam", listOf(
                            EntityModel(
                                "steam account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            )
                        )
                    ),
                    FolderModel(
                        "epic", listOf()
                    ),
                    FolderModel(
                        "lineage", listOf(
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            ),
                            EntityModel(
                                "lineage account",
                                "username",
                                "password",
                                "url",
                                "notes"
                            )
                        )
                    )
                )
            )
        )
    }

}
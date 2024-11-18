package com.extaleusinc.data.repository.home

import com.extaleusinc.data.model.FoldersModel

interface HomeRepository {
    fun getFolders(): Result<FoldersModel>
}

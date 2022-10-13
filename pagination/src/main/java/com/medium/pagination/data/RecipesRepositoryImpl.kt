package com.medium.pagination.data

import com.medium.pagination.domain.RecipesPaginationModel
import com.medium.pagination.domain.RecipesRepository
import com.medium.pagination.domain.toModel
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesApiService: RecipesApiService
) : RecipesRepository {

    override suspend fun getRecipes(from: Int, size: Int): RecipesPaginationModel {
        return recipesApiService.getRecipes(from = from, size = size).toModel()
    }
}

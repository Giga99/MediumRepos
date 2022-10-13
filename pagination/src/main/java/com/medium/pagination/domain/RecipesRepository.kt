package com.medium.pagination.domain

interface RecipesRepository {

    suspend fun getRecipes(from: Int, size: Int): RecipesPaginationModel
}

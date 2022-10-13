package com.medium.pagination.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApiService {

    @GET("recipes/list")
    suspend fun getRecipes(
        @Query("from") from: Int,
        @Query("size") size: Int
    ): RecipesPaginationResponse
}

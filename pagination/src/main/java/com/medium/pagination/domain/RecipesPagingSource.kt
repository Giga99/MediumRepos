package com.medium.pagination.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class RecipesPagingSource @Inject constructor(
    private val recipesRepository: RecipesRepository
) : PagingSource<Int, RecipeModel>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeModel> =
        try {
            val page = params.key ?: 0
            val size = params.loadSize
            val from = page * size
            val data = recipesRepository.getRecipes(from = from, size = size)
            val itemsAfter = data.count - from + data.results.size
            LoadResult.Page(
                data = data.results,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (data.results.isEmpty()) null else page + 1,
                itemsAfter = if (itemsAfter > size) size else itemsAfter.toInt(),
                itemsBefore = from
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}

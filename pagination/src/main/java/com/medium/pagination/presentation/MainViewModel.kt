package com.medium.pagination.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.medium.pagination.domain.RecipesPagingSource
import com.medium.pagination.domain.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    val recipes = Pager(
        pagingSourceFactory = { RecipesPagingSource(recipesRepository) },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)
}

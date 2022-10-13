package com.medium.pagination.domain

import com.medium.pagination.data.RecipeResponse
import com.medium.pagination.data.RecipesPaginationResponse
import com.medium.pagination.data.UserRatingsResponse

fun RecipesPaginationResponse.toModel(): RecipesPaginationModel =
    RecipesPaginationModel(
        count = count,
        results = results.map { it.toModel() }
    )

fun RecipeResponse.toModel(): RecipeModel =
    RecipeModel(
        id = id,
        name = name,
        thumbnailUrl = thumbnail_url,
        thumbnailAltText = thumbnail_alt_text,
        userRatings = user_ratings?.toModel()
    )

fun UserRatingsResponse.toModel(): UserRatingsModel =
    UserRatingsModel(
        countPositive = count_positive,
        countNegative = count_negative,
        score = score
    )

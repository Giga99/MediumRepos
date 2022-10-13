package com.medium.pagination.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipesPaginationResponse(
    val count: Long,
    val results: List<RecipeResponse>
)

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val id: Long,
    val name: String,
    val thumbnail_alt_text: String,
    val thumbnail_url: String,
    val user_ratings: UserRatingsResponse?
)

@JsonClass(generateAdapter = true)
data class UserRatingsResponse(
    val count_negative: Long,
    val count_positive: Long,
    val score: Float?
)

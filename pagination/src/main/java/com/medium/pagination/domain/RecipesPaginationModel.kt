package com.medium.pagination.domain

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

data class RecipesPaginationModel(
    val count: Long,
    val results: List<RecipeModel>
)

data class RecipeModel(
    val id: Long,
    val name: String,
    val thumbnailAltText: String,
    val thumbnailUrl: String,
    val userRatings: UserRatingsModel?
) {
    fun getRating(): String {
        val format = DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.US)).apply {
            positiveSuffix = "/5.00"
            roundingMode = RoundingMode.HALF_EVEN
        }
        return format.format((userRatings?.score ?: 0f) * 100f / 20f)
    }
}

data class UserRatingsModel(
    val countNegative: Long,
    val countPositive: Long,
    val score: Float?
)

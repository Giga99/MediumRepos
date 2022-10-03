package com.medium.navigation.navigation_utils

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object HomeScreen : NoArgumentsDestination("home")

    object UsersScreen : NoArgumentsDestination("users")

    object MessagesScreen : NoArgumentsDestination("messages")

    object DetailsScreen : NoArgumentsDestination("details")

    object UserDetailsScreen : Destination("user_details", "firstName", "lastName") {
        const val FIST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"

        operator fun invoke(fistName: String, lastName: String): String = route.appendParams(
            FIST_NAME_KEY to fistName,
            LAST_NAME_KEY to lastName
        )
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}

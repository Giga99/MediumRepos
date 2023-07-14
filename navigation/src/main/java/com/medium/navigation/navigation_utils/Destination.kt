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

    object HomeScreen : NoArgumentsDestination(HOME_ROUTE)

    object UsersScreen : NoArgumentsDestination(USERS_ROUTE)

    object MessagesScreen : NoArgumentsDestination(MESSAGES_ROUTE)

    object DetailsScreen : NoArgumentsDestination(DETAIL_ROUTE)

    object UserDetailsScreen : Destination(USER_DETAIL_ROUTE, FIST_NAME_KEY, LAST_NAME_KEY) {
        operator fun invoke(fistName: String, lastName: String): String = route.appendParams(
            FIST_NAME_KEY to fistName,
            LAST_NAME_KEY to lastName
        )
    }

    companion object {
        private const val HOME_ROUTE = "home"
        private const val USERS_ROUTE = "users"
        private const val MESSAGES_ROUTE = "messages"
        private const val DETAIL_ROUTE = "details"
        private const val USER_DETAIL_ROUTE = "user_details"
        private const val FIST_NAME_KEY = "firstName"
        private const val LAST_NAME_KEY = "lastName"
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

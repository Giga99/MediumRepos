package com.medium.navigation.users

data class UsersViewState(
    val users: List<User> = listOf(
        User("Firstname1", "Lastname1"),
        User("Firstname2", "Lastname2"),
        User("Firstname3", "Lastname3"),
        User("Firstname4", "Lastname4"),
        User("Firstname5", "Lastname5")
    )
)

data class User(
    val firstName: String,
    val lastName: String
)

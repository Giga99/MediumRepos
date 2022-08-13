package com.medium.interceptors

interface AuthRepository {

    fun refreshToken(refreshToken: String): String

    fun logout()
}

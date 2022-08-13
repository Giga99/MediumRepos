package com.medium.interceptors

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override fun refreshToken(refreshToken: String): String {
        /* .... */
        return ""
    }

    override fun logout() {
        /* .... */
    }
}

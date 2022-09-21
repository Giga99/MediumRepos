package com.medium.interceptors

import javax.inject.Inject

class SessionManager @Inject constructor(
    private val pref: AppSharedPreferences,
    private val authRepository: AuthRepository
) {

    fun getAccessToken(): String? = pref.getAccessToken()

    fun getRefreshToken(): String? = pref.getRefreshToken()

    fun refreshToken(refreshToken: String): String = authRepository.refreshToken(refreshToken)

    fun logout() {
        /* .... */
        pref.setAccessToken("")
    }
}
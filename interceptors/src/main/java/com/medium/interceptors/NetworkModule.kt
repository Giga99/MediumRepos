package com.medium.interceptors

import android.content.Context
import android.content.SharedPreferences
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences =
        context.getSharedPreferences(AppSharedPreferences.SHARED_PREFS, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ) = AppSharedPreferences(sharedPreferences)

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Singleton
    @Provides
    fun provideAuthInterceptorImpl(
        appSharedPreferences: AppSharedPreferences,
        authRepository: AuthRepository
    ): AuthInterceptorImpl = AuthInterceptorImpl(appSharedPreferences, authRepository)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptorImpl
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: Lazy<OkHttpClient>
    ): Retrofit = Retrofit.Builder()
        .baseUrl("BASE_URL")
        .callFactory { okHttpClient.get().newCall(it) }
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

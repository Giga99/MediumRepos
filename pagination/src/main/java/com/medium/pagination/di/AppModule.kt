package com.medium.pagination.di

import com.medium.pagination.data.RecipesApiService
import com.medium.pagination.data.RecipesRepositoryImpl
import com.medium.pagination.domain.RecipesRepository
import com.medium.pagination.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .header("X-RapidAPI-Key", "d256ce8a44mshd38aabe8b4270f2p147ee4jsn2af970cbf203")
                    .header("X-RapidAPI-Host", "tasty.p.rapidapi.com")
                    .build()
                it.proceed(request)
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideRecipesApiService(retrofit: Retrofit): RecipesApiService = retrofit.create()

    @Singleton
    @Provides
    fun provideRecipesRepository(recipesApiService: RecipesApiService): RecipesRepository =
        RecipesRepositoryImpl(recipesApiService = recipesApiService)
}

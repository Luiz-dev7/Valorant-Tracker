package com.luiz.valorantapi.di

import com.luiz.valorantapi.BuildConfig
import com.luiz.valorantapi.data.api.RiotApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.HENRIK_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.henrikdev.xyz/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRiotApi(retrofit: Retrofit): RiotApi =
        retrofit.create(RiotApi::class.java)
}
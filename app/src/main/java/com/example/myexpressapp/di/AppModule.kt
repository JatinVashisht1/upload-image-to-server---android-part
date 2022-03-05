package com.example.myexpressapp.di

import android.app.Application
import com.example.myexpressapp.data.MyApi
import com.example.myexpressapp.data.repository.MyRepoImpl
import com.example.myexpressapp.domain.repository.MyRepo
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("http://localhost:3000")
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson)
            )
            .build()
            .create(MyApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMyRepo(api: MyApi): MyRepo = MyRepoImpl(
        api = api,
        application = Application()
    )
}
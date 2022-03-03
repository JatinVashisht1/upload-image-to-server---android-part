package com.example.myexpressapp.di

import com.example.myexpressapp.data.MyApi
import com.example.myexpressapp.data.repository.MyRepoImpl
import com.example.myexpressapp.domain.repository.MyRepo
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
    fun provideMyApi(): MyApi = Retrofit.Builder()
        .baseUrl("http://localhost:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)

    @Provides
    @Singleton
    fun provideMyRepo(api: MyApi): MyRepo = MyRepoImpl(api = api)
}
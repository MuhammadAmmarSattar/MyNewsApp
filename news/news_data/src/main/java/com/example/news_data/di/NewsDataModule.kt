package com.example.news_data.di

import com.example.news_data.network.NewsAPIService
import com.example.news_data.repository.NewsRepositoryImpl
import com.example.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn
@Module
object NewsDataModule {

    @Provides
    fun provideProvider(retrofit: Retrofit):NewsAPIService{
        return retrofit.create(NewsAPIService::class.java)
    }


    @Provides
    fun provideNewsRepository(newsAPIService: NewsAPIService):NewsRepository{
        return NewsRepositoryImpl(newsAPIService)
    }
}
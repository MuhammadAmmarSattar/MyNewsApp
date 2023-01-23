package com.example.news_data.di

import com.example.news_data.network.NewsAPIService
import com.example.news_data.repository.NewsRepositoryImpl
import com.example.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NewsDataModule {


//    @Provides
//    fun provideNewsApiService(retrofit: Retrofit): NewsAPIService {
//        return retrofit.create(NewsAPIService::class.java)
//    }
//
//
//    @Provides
//    fun provideNewsRepository(newsApiService: NewsAPIService):NewsRepository{
//        return NewsRepositoryImpl(newsApiService)
//    }


    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsAPIService {
        return retrofit.create(NewsAPIService::class.java)
    }


    @Provides
    fun provideNewsRepository(newsApiService: NewsAPIService):NewsRepository{
        return NewsRepositoryImpl(newsApiService)
    }



}
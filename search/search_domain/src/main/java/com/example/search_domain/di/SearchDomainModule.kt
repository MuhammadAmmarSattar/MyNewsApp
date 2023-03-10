package com.example.search_domain.di

import com.example.search_domain.repository.SearchRepository
import com.example.search_domain.use_case.GetSearchArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class SearchDomainModule {

    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository):GetSearchArticleUseCase{
        return GetSearchArticleUseCase(searchRepository)
    }
}
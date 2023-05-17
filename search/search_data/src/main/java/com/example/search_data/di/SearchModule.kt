package com.example.search_data.di

import com.example.search_data.network.SearchAPI
import com.example.search_data.repository.SearchRepositoryImpl
import com.example.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@InstallIn(SingletonComponent::class)
@Module
object SearchModule {

    @Provides
    fun provideSearchAPI(retrofit: Retrofit):SearchAPI
    = retrofit.create(SearchAPI::class.java)

    @Provides
    fun provideSearchRepository(searchAPI: SearchAPI):SearchRepository
        = SearchRepositoryImpl(searchAPI)


}
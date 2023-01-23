package com.example.search_data.di

import com.example.search_data.network.SearchAPi
import com.example.search_data.repository.SearchResponseImpl
import com.example.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Provides
    fun provideSearchAPI(retrofit: Retrofit):SearchAPi{
        return retrofit.create(SearchAPi::class.java)
    }

    @Provides
    fun provideSearch(searchAPi: SearchAPi): SearchRepository{
        return SearchResponseImpl(searchAPi)
    }


}
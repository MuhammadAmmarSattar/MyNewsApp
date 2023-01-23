package com.example.newsapp.di

import android.content.Context
import com.example.news_data.room.NewsDao
import com.example.newsapp.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {

    @Provides
    @Singleton
    fun provideNewsData(@ApplicationContext context: Context):AppDataBase{
        return  AppDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideNewsDAO(appDataBase: AppDataBase):NewsDao{
        return appDataBase.getNewsDao()
    }

}
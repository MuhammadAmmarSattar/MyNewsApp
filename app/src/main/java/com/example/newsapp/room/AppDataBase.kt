package com.example.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news_data.room.NewsDao
import com.example.news_domain.model.Article

@Database(entities = [Article::class] , version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
                .fallbackToDestructiveMigration().build()
        }
    }
    abstract fun getNewsDao():NewsDao
}
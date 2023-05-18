package com.example.search_data.mapper

import com.example.core_ui.model.Article
import com.example.search_data.model.ArticleDTO


fun ArticleDTO.toDomainArticle(): Article {

    return Article(
        author = this.author?:"",
        content = this.content?:"",
        description = this.description?:"",
        title = this.title?:"",
        urlToImage = this.urlToImage?:""
    )
}
package com.almogdadjabir.com.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.almogdadjabir.com.model.remote.Source

@Entity(tableName = "articles_table")
data class ArticlesEntity(
    @PrimaryKey var id: Int?,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)
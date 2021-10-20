package com.almogdadjabir.com.repository

import com.almogdadjabir.com.model.remote.APIData
import com.almogdadjabir.com.model.local.ArticlesDAO
import com.almogdadjabir.com.model.local.ArticlesEntity
import com.almogdadjabir.com.other.Constants.URL
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class Repository @Inject constructor(private val client: HttpClient, private val dao: ArticlesDAO) {


    suspend fun insertNewsIntoDB(articles: ArticlesEntity) {
        dao.insert(articles)
    }

    suspend fun getAllLocalNews(): List<ArticlesEntity> {
        return dao.getAllLocalNews()
    }

    suspend fun getAllRemoteNews(): APIData {
        return client.get(urlString = URL) as APIData
    }
}
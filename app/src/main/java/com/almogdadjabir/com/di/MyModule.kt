package com.almogdadjabir.com.di

import android.content.Context
import androidx.room.Room
import com.almogdadjabir.com.model.local.ArticlesDAO
import com.almogdadjabir.com.model.local.ArticlesDataBase
import com.almogdadjabir.com.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Singleton
    @Provides
    fun providesContextInstance(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun providesKTORInstance(): HttpClient {
        val json = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
        val client = HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = json)
            }
            install(Logging){
                level = LogLevel.ALL
            }
        }
        return client
    }

    @Singleton
    @Provides
    fun providesRepositoryInstance(client: HttpClient, dao: ArticlesDAO) = Repository(client, dao)

    @Singleton
    @Provides
    fun providerDataBaseInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ArticlesDataBase::class.java,
            "news_database"
        ).build()

    @Singleton
    @Provides
    fun providerDAOInstance(articlesDataBase: ArticlesDataBase) = articlesDataBase.getDao()

    @Singleton
    @Provides
    fun providerQuranDAOInstance(articlesDataBase: ArticlesDataBase) = articlesDataBase.getQuranDao()
}
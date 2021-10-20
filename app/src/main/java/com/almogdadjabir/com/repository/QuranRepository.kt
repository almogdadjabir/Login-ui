package com.almogdadjabir.com.repository

import android.util.Log
import com.almogdadjabir.com.model.local.ChapterDAO
import com.almogdadjabir.com.model.local.ChapterEntity
import com.almogdadjabir.com.model.remote.APIQuranData
import com.almogdadjabir.com.model.remote.translations.APITranslationsData
import com.almogdadjabir.com.model.remote.verses.APIVersesData
import com.almogdadjabir.com.other.Constants.quran_URL
import com.almogdadjabir.com.other.Constants.translation_URL
import com.almogdadjabir.com.other.Constants.verses_URL
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class QuranRepository @Inject constructor(private val client: HttpClient, private val dao: ChapterDAO) {




    suspend fun getAllLocalChapter(): List<ChapterEntity> {
        return dao.getAllLocalChapter()
    }

    suspend fun getAllRemoteChapter(): APIQuranData {
        return client.get(urlString = quran_URL) as APIQuranData
    }

    suspend fun getAllRemoteVerses(id: Int): APIVersesData {
        Log.i("id.toString()","${id.toString()}")
        // for replacing * with the chapters id
        verses_URL = verses_URL.replace("@",newValue = id.toString())
        Log.i("verses_URL","${verses_URL}")
        return client.get<APIVersesData>{
            Log.d("onSuccess vers","${url(translation_URL)}")

            url(verses_URL)
        }
    }

    suspend fun getAllRemoteTranslations(id: Int): APITranslationsData {
        Log.i("Trans id.toString()","${id.toString()}")
        // for replacing * with the chapters id
        translation_URL = translation_URL.replace("@",newValue = id.toString())
        Log.i("translation_URL","${translation_URL}")
        return client.get<APITranslationsData>{
            Log.d("onSuccess ana","${url(translation_URL)}")

            url(translation_URL)
        }
    }

    suspend fun insertChapterIntoDB(chapters: ChapterEntity) {
        dao.insert(chapters)
    }
}
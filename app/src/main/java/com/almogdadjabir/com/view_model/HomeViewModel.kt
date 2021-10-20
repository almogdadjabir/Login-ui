package com.almogdadjabir.com.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almogdadjabir.com.model.local.ArticlesEntity
import com.almogdadjabir.com.model.remote.Articles
import com.almogdadjabir.com.other.Constants
import com.almogdadjabir.com.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("StaticFieldLeak")

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val repository: Repository,
    private val context: Context
) :
    ViewModel() {

    private var _allNews = MutableLiveData<List<Articles>>(null)
    val allNews: LiveData<List<Articles>> = _allNews


    init {
        getAllNew()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getAllNew() {
        if (Constants.isOnline(context)) {
            Constants.createToast("Online Mode !!", context)
            getRemoteNews()
        } else {
            Constants.createToast("Offline Mode !!", context)
            getLocalNews()
        }
    }

    private fun getRemoteNews() {
        Log.d("ay kee","ay key");
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllRemoteNews()
            }.onSuccess {
                val data = it.articles
                _allNews.value = data
                Log.d("ay kee","${data!!.size}");

                insertNewsToDB()
            }.onFailure {
                _allNews.value = null
                Log.d("ay kee","err");

            }
        }
    }

    private fun insertNewsToDB() {
        viewModelScope.launch {
            var id = 1
            allNews.value?.forEach {
                repository.insertNewsIntoDB(
                    ArticlesEntity(
                        id,
                        it.source,
                        it.author,
                        it.title,
                        it.description,
                        it.url,
                        it.urlToImage,
                        it.publishedAt,
                        it.content
                    )
                )
                ++id
            }
        }
    }

    private fun getLocalNews() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllLocalNews()
            }.onSuccess {
                _allNews.value = null
                val list = mutableListOf<Articles>()
                it.forEach { item ->
                    list.add(
                        Articles(
                            item.source,
                            item.author,
                            item.title,
                            item.description,
                            item.url,
                            item.urlToImage,
                            item.publishedAt,
                            item.content
                        )
                    )
                }
                _allNews.value = list.toList()
            }.onFailure {
                _allNews.value = null
            }
        }
    }

}
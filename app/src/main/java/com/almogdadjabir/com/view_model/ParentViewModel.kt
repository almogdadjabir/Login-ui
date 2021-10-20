package com.almogdadjabir.com.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almogdadjabir.com.model.remote.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParentViewModel @Inject constructor() : ViewModel() {
    lateinit var articles: LiveData<Articles>
    fun getArticlesObject(item: Articles) {
        articles = MutableLiveData(item)
    }
}
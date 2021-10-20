package com.almogdadjabir.com.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almogdadjabir.com.model.remote.Articles
import com.almogdadjabir.com.model.remote.Chapters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuranParentViewModel @Inject constructor() : ViewModel() {
    lateinit var chaptrs: LiveData<Chapters>
    fun getChaptrsObject(item: Chapters) {
        chaptrs = MutableLiveData(item)
    }
}
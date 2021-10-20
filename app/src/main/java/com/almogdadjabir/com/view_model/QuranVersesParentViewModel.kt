package com.almogdadjabir.com.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.almogdadjabir.com.model.remote.Articles
import com.almogdadjabir.com.model.remote.Chapters
import com.almogdadjabir.com.model.remote.verses.Verses
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuranVersesParentViewModel @Inject constructor() : ViewModel() {
    lateinit var verses: LiveData<Verses>
    fun getVersesObject(item: Verses) {
        verses = MutableLiveData(item)
    }
}
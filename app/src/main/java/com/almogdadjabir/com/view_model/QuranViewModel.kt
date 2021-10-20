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
import com.almogdadjabir.com.model.remote.Chapters
import com.almogdadjabir.com.other.Constants
import com.almogdadjabir.com.repository.QuranRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("StaticFieldLeak")

@HiltViewModel
class QuranViewModel
@Inject constructor(
    private val repository: QuranRepository,
    private val context: Context
) :
    ViewModel() {

    private var _allChapter = MutableLiveData<List<Chapters>>(null)
    val allChapter: LiveData<List<Chapters>> =  _allChapter


    init {
        getAllChapter()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getAllChapter() {
        if (Constants.isOnline(context)) {
            Constants.createToast("Online Mode !!", context)
            getRemoteChapter()
        } else {
            Constants.createToast("Offline Mode !!", context)
//            getLocalChapter()
        }
    }

    private fun getRemoteChapter() {
        Log.d("getRemoteChapter","shqala")
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllRemoteChapter()
            }.onSuccess {
                val data = it.chapters
                _allChapter.value = data
//                insertChapterToDB()
                Log.d("getRemoteChapter","onSuccess")

            }.onFailure {
                _allChapter.value = null
                Log.d("getRemoteChapter","onFailure")

            }
        }
    }

//    private fun insertChapterToDB() {
//        viewModelScope.launch {
//            var id = 1
//            allChapter.value?.forEach {
//                repository.insertChapterIntoDB(
//                    ChapterEntity(
//                        id,
//                        it.revelation_place,
//                        it.revelation_order,
//                        it.,
//                        it.name_simple,
//                        it.name_arabic,
//                        it.verses_count
//                    )
//                )
//                ++id
//            }
//        }
//    }

//    private fun getLocalChapter() {
//        viewModelScope.launch {
//            kotlin.runCatching {
//                repository.getAllLocalChapter()
//            }.onSuccess {
//                _allChapter.value = null
//                val list = mutableListOf<Chapters>()
//                it.forEach { item ->
//                    list.add(
//                        Chapters(
//                            item.evelation_place,
//                            item.revelation_order,
//                            item.bismillah_pre,
//                            item.name_simple,
//                            item.name_arabic,
//                            item.verses_count,
//                            item.id
//                        )
//                    )
//                }
//                _allChapter.value = list.toList()
//            }.onFailure {
//                _allChapter.value = null
//            }
//        }
//    }

}
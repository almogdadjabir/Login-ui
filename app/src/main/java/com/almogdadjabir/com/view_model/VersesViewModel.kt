package com.almogdadjabir.com.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almogdadjabir.com.model.remote.Chapters
import com.almogdadjabir.com.model.remote.verses.Verses
import com.almogdadjabir.com.other.Constants
import com.almogdadjabir.com.repository.QuranRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("StaticFieldLeak")

@HiltViewModel
class VersesViewModel
@Inject constructor(
    private val repository: QuranRepository,
    private val context: Context
) :
    ViewModel() {

    private var _allVerses = MutableLiveData<List<Verses>>(null)
    public val allVerses: LiveData<List<Verses>> = _allVerses
//    init {
//        getAllVerses()
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    public fun getAllVerses(id: Int) {
        if (Constants.isOnline(context)) {
            Constants.createToast("Online Mode !!", context)
            getRemoteVerses(id)
        } else {
            Constants.createToast("Offline Mode !!", context)
//            getLocalVerses()
        }
    }

    private fun getRemoteVerses(id: Int) {
        Log.d("getRemoteChapter",id.toString())
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllRemoteVerses(id)
            }.onSuccess {
                val data = it.verses
                _allVerses.value = data
//                insertChapterToDB()
                Log.d("getRemoteChapter","onSuccess")

            }.onFailure {
                _allVerses.value = null
                Log.d("onFailure","${it.message}")

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
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
import com.almogdadjabir.com.model.remote.aya.Ayah
import com.almogdadjabir.com.model.remote.verses.Verses
import com.almogdadjabir.com.other.Constants
import com.almogdadjabir.com.repository.QuranRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("StaticFieldLeak")

@HiltViewModel
class AyaViewModel
@Inject constructor() :
    ViewModel() {

    private var _allAyah = MutableLiveData<List<Ayah>>(null)
    val allAyah: LiveData<List<Ayah>> = _allAyah


    @RequiresApi(Build.VERSION_CODES.M)
    public fun getAllAyah(id: Int) {
        //_allAyah.verses

    }

}
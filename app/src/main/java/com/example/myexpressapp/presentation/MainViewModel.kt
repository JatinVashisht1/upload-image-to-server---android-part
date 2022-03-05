package com.example.myexpressapp.presentation

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexpressapp.MyApplication
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.domain.repository.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myRepo: MyRepo,
    private val myApplication : Application
) : AndroidViewModel(myApplication) {
    private val _myData = mutableStateOf<DtoResponse>(DtoResponse())
    val myData = _myData

    init {

        viewModelScope.launch {
            getResponse()
        }
    }

    suspend fun getResponse(){
        val response = myRepo.getResponse()
        Log.d("Response", response.toString())
    }

    fun postImage() {
        viewModelScope.launch {
            val stream = myApplication.resources.assets.open("eminem.jpg")
            val bitmap = BitmapFactory.decodeStream(stream)
            myRepo.postImage(bitmap)
        }
    }
}
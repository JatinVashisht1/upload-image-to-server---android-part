package com.example.myexpressapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.data.dto.DtoResponseItem
import com.example.myexpressapp.domain.repository.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myRepo: MyRepo
) : ViewModel() {
    private val _myData = mutableStateOf<DtoResponse>(DtoResponse())
    val myData = _myData

    init {
        viewModelScope.launch {
            getData()
        }
    }

    suspend fun getData() {
        _myData.value = myRepo.getResponse()
    }

    suspend fun pushData(dtoResponse: ArrayList<DtoPostObject>){
        myRepo.pushResponse(dtoResponse = dtoResponse)
    }
}
package com.bhaveshp750.junoassignment.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaveshp750.junoassignment.modal.ApiClient
import com.bhaveshp750.junoassignment.modal.HomeDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiClient
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val fetchError = MutableLiveData<Boolean>()
    val homeDto = MutableLiveData<HomeDto>()

    init {
        getValueState()
    }

    private fun getValueState() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                homeDto.value = api.getHome()
                fetchError.value = false
                isLoading.value = false
            } catch (e: Exception) {
                Log.e(TAG, "getValueState: ", e)
                fetchError.value = true
                isLoading.value = false
            }
        }
    }

    private fun getEmptyState() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                homeDto.value = api.getHome()
                fetchError.value = false
                isLoading.value = false
            } catch (e: Exception) {
                Log.e(TAG, "getValueState: ", e)
                fetchError.value = true
                isLoading.value = false
            }
        }
    }
}
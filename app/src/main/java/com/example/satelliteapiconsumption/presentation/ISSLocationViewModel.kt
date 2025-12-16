package com.example.satelliteapiconsumption.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satelliteapiconsumption.data.model.ISSModel
import com.example.satelliteapiconsumption.repository.IssLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISSLocationViewModel @Inject constructor(
    private val repository: IssLocationRepository
) : ViewModel(){

    // create variable to hold state/data of api call and to pass to the UI
    private val _uiState = MutableStateFlow<ISSLocationState>(ISSLocationState.Loading)
    val uiState: StateFlow<ISSLocationState> = _uiState

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            // this while loop uses inActive which returns true when coroutine is alive
            // used for polling or getting periodic update from api call where data is constantly changing
            while (currentCoroutineContext().isActive) {
                try {
                    val data = repository.getISSLocation()
                    _uiState.value = ISSLocationState.Success(data)
                } catch (e: Exception) {
                    _uiState.value = ISSLocationState.Error(e.message ?: "Unknown error")
                }
                delay(1000)
            }

        }
    }

}

sealed class ISSLocationState{
    // loading, success, error
    object Loading: ISSLocationState()
    data class Success(val data: ISSModel): ISSLocationState()
    data class Error(val error:String): ISSLocationState()
}
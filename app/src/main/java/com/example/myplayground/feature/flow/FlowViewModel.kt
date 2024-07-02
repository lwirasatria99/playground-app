package com.example.myplayground.feature.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayground.helper.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FlowViewModel(
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>(5)
    val shareFlow = _sharedFlow.asSharedFlow()

    val countDownFlow = flow {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while(currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }.flowOn(dispatchers.main)

    fun squareNumber(value: Int) {
        viewModelScope.launch(dispatchers.main) {
            _sharedFlow.emit(value * value)
        }
    }
}
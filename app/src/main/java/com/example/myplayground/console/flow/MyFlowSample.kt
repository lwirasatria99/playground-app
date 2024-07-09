package com.example.myplayground.console.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class CounterState(val count: Int)

fun main() = runBlocking {
    hotFlow()
}

private suspend fun hotFlow() {
    val stateFlow = MutableStateFlow(CounterState(0))

    coroutineScope {
        launch {
            repeat(5) {
                delay(500)
                stateFlow.value = stateFlow.value.copy(count = stateFlow.value.count + 1)
            }
        }
        launch {
            stateFlow.collectLatest { state ->
                println("Current count: ${state.count}")
            }
        }
    }

    delay(2000)
}

private suspend fun coldFlow() {
    val numberFlow: Flow<Int> = flow {
        for (i in 1..5) {
            delay(100)
            emit(i)
        }
    }

    numberFlow.collect { value ->
        println("Received: $value")
    }

    numberFlow
        .map { it * 2 }
        .filter { it % 3 == 0 }
        .collect { value ->
            println("Filtered value: $value")
        }
}
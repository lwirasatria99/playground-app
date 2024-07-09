package com.example.myplayground.console.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

data class MyUser(val username: String, val password: String)

fun loginFlow(username: String, password: String = "wira123"): Flow<MyUser> = flow {
    delay(1000)
    emit(MyUser(username, password))
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val loginAttemps = flow {
        emit(loginFlow("Wira"))
        delay(500)
        emit(loginFlow("Mahendra"))
        delay(500)
    }.flattenConcat()

    loginAttemps.collectLatest { myUser ->
        println("Logged in as: ${myUser.username} with token: ${myUser.password}")
    }
}
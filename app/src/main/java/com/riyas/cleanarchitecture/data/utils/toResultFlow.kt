package com.riyas.cleanarchitecture.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> toResultFlow(
    isOnline: Boolean,
    call: suspend () -> T?
): Flow<NetWorkResult<T>> {
    return flow {
        emit(NetWorkResult.Loading)
        if (isOnline) {
            try {
                val c = call()
                c.let { response ->
                    println("response${response}")
                    emit(NetWorkResult.Success(response))
                }
            } catch (e: Exception) {
                emit(NetWorkResult.Error(null, e.toString()))
            }
        } else {
            emit(NetWorkResult.Error(exception = "No connectivity"))
        }
    }
}
